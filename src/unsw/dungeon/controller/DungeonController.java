package unsw.dungeon.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import unsw.dungeon.loader.DungeonControllerLoader;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.goal.Goal;
import unsw.dungeon.model.inventory.Inventory;
import unsw.dungeon.model.status.Status;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.util.List;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonController {

	@FXML
	private GridPane squares;
	@FXML
	private VBox gameInfo;
	@FXML
	private VBox inventoryInfo;
	@FXML
	private VBox goalInfo;
	@FXML
	private HBox root;
	@FXML
	private Label bombInfo;
	@FXML
	private Label swordInfo;
	@FXML
	private Label treasureInfo;
	@FXML
	private Label keyInfo;

	@FXML
	private Button pauseButton;
	@FXML
	private Button retryButton;
	@FXML
	private Button menuButton;


	private MenuScreen menuScreen;
	private DungeonScreen currDungeonScreen;
	private DungeonScreen nextDungeonScreen;
	private DungeonScreen prevDungeonScreen;

	private boolean isPaused = false;

	private List<ImageView> initialEntities;
	private Dungeon dungeon;
	private DungeonControllerLoader loader;
	private Timeline timeline;


	public DungeonController(DungeonScreen screen) {
		this.currDungeonScreen = screen;
	}

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		setDungeon(dungeon);
		setLoader(loader);
		setInitialEntities(initialEntities);
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.setController(this);
	}

	public void setInitialEntities(List<ImageView> initialEntities) {
		this.initialEntities = initialEntities;
	}

	public void setLoader(DungeonControllerLoader loader) {
		this.loader = loader;
	}

	public void startDungeon() {
		if (dungeon != null) {
			trackInventory();
			trackStatus();
			this.timeline.play();
			this.playBGM();
		} else {
			System.out.println("Dungeon has not been set!");
		}
	}

	@FXML
	public void initialize() {
		// timeline
		this.timeline = new Timeline();
		this.timeline.setCycleCount(Animation.INDEFINITE);
		this.timeline.setAutoReverse(false);
		this.timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500),
						e -> {if (!dungeon.isGameOver()) this.dungeon.tick();}));

		// Add the ground first so it is below all other entities
		Image ground = new Image("/dirt_0_new.png");
		for (int x = 0; x < dungeon.getWidth(); x++) {
			for (int y = 0; y < dungeon.getHeight(); y++) {
				squares.add(new ImageView(ground), x, y);
			}
		}
		// add all the entities into the dungeon
		for (ImageView entity : initialEntities)
			squares.getChildren().add(entity);

		trackEntities();
		trackInventory();
		trackStatus();
		trackGoal();
	}

	void trackEntities() {
		dungeon.getEntitiesProperty().addListener(
				new ListChangeListener<Entity>() {
					public void onChanged(Change change) {
						while (change.next()) {
							for (Object remitem : change.getRemoved())
								removeEntityImage((Entity) remitem);
							for (Object additem : change.getAddedSubList())
								addEntityImage((Entity) additem);
						}
					}
				});
	}

	private void removeEntityImage(Entity entity) {
		this.squares.getChildren().remove(entity.getNode());
	}

	private void addEntityImage(Entity entity) {
		this.loader.onLoad(entity);
		this.squares.getChildren().add(entity.getNode());
	}


	// TODO set the game info pane
	private void trackInventory() {
		Inventory inventory = dungeon.getInventory();

		// treasure
		treasureInfo.setText("Collected Treasures: " + inventory.getNumTreasuresProperty().get());
		inventory.getNumTreasuresProperty().addListener(
				(observable, oldValue, newValue) ->
						treasureInfo.setText("Collected Treasures: " + newValue)
		);
		// bomb
		bombInfo.setText("Collected Bombs: " + inventory.getBombNumProperty().get());
		inventory.getBombNumProperty().addListener(
				(observable, oldValue, newValue) -> {
					if ((int) newValue > 0) {
						bombInfo.setVisible(true);
						bombInfo.setText("Collected Bombs: " + newValue);
					} else
						bombInfo.setVisible(false);
				});
		// sword
		if (inventory.getSwordDurabilityProperty().get() > 0) {
			swordInfo.setVisible(true);
			swordInfo.setText("Sword Durability: " + inventory.getSwordDurabilityProperty().get());
		} else
			swordInfo.setVisible(false);
		inventory.getSwordDurabilityProperty().addListener(
				(observable, oldValue, newValue) -> {
					if ((int) newValue > 0) {
						swordInfo.setVisible(true);
						swordInfo.setText("Sword Durability: " + newValue);
					} else
						swordInfo.setVisible(false);
				});
		// key
		if ((int) inventory.getKeyIDProperty().get() >= 0) {
			keyInfo.setVisible(true);
			keyInfo.setText("Key");
		} else
			keyInfo.setVisible(false);
		inventory.getKeyIDProperty().addListener(
				(observable, oldValue, newValue) -> {
					if ((int) newValue >= 0) {
						keyInfo.setVisible(true);
						keyInfo.setText("Key");
					} else
						keyInfo.setVisible(false);
				});
	}

	private void trackStatus() {
		Status status = dungeon.getStatus();

		// invincible
		status.getInvincibleRemainingProperty().addListener(
				(observable, oldValue, newValue) -> {
					Node player = dungeon.getPlayer().getNode();
					if ((int) newValue > 0) {
						ColorAdjust colorAdjust = new ColorAdjust();
						colorAdjust.setSaturation((int) newValue * 0.2);
						player.setEffect(colorAdjust);
					} else
						player.setEffect(null);
				});
		// invisible
		status.getInvisibleRemainingProperty().addListener(
				(observable, oldValue, newValue) -> {
					Node player = dungeon.getPlayer().getNode();
					if ((int) newValue > 0) {
						ColorAdjust colorAdjust = new ColorAdjust();
						colorAdjust.setBrightness(0.3 + (int) newValue * 0.05);
						player.setEffect(colorAdjust);
					} else
						player.setEffect(null);
				});

	}

	private void trackGoal() {
		Goal goal = dungeon.getGoal();
		addGoalInfo(goal, goalInfo);
	}

	private void addGoalInfo(Goal goal, Pane pane) {
		VBox subpane = new VBox();
		subpane.setPadding(new Insets(0, 0, 0, 10));
		pane.getChildren().add(subpane);

		CheckBox goal_check = new CheckBox(goal.toString()) {
			@Override
			public void arm() {} // readonly checkbox
		};
		goal_check.setPadding(new Insets(0, 5, 5, 0));
		goal.getSatisfiedProperty().addListener(
				(observable, oldValue, newValue) -> goal_check.setSelected(newValue)
		);
		subpane.getChildren().add(goal_check);

		if (!goal.isLeaf()) {
			for (Goal subgoal : goal.getSubgoals())
				addGoalInfo(subgoal, subpane);
		}
	}


	@FXML
	public void handleKeyPress(KeyEvent event) {
		if (isPaused) {
			if (event.getCode() == KeyCode.ESCAPE)
				pause();
			return;
		}

		switch (event.getCode()) {
			case W:
				dungeon.movePlayer(Direction.UP);
				break;
			case S:
				dungeon.movePlayer(Direction.DOWN);
				break;
			case A:
				dungeon.movePlayer(Direction.LEFT);
				break;
			case D:
				dungeon.movePlayer(Direction.RIGHT);
				break;
			case U:
				dungeon.placeBomb();
				break;
			case ESCAPE:
				pause();
				break;
			default:
				break;
		}
	}


	// game pause

	private void pause() {
		if (!isPaused)
			pauseGame();
		else
			resumeGame();
		pauseButton.setText(isPaused ? "Resume" : "Pause");
		retryButton.setVisible(isPaused);
		menuButton.setVisible(isPaused);
	}

	private void pauseGame() {
		timeline.stop();
		darkleScreen();
		this.isPaused = true;
	}

	private void resumeGame() {
		timeline.play();
		lightenScreen();
		this.isPaused = false;
	}

	void darkleScreen() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.8);
		squares.setEffect(colorAdjust);
	}

	void lightenScreen() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(0);
		squares.setEffect(colorAdjust);
	}


	// game over
	public void gameOver(String gameOverInfo) {
		DungeonSoundPlayer.stopBGM();
		getTimeline().stop();

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: #000000");

		Text info = new Text("Game Over \n\n" + gameOverInfo + "\n\n");
		info.setTextAlignment(TextAlignment.CENTER);
		info.setFont(new Font(18));
		info.setFill(Color.ALICEBLUE);

		Button backButton = new Button("Back to Menu");
		backButton.setPadding(new Insets(10, 10, 10, 10));
		backButton.setOnAction(event -> menuScreen.start());

		Button retryButton = new Button("Retry");
		retryButton.setPadding(new Insets(10, 10, 10, 10));
		retryButton.setOnAction(event -> this.restart());

		VBox vbox = new VBox();
		vbox.getChildren().addAll(info, backButton, retryButton);
		vbox.setAlignment(Pos.CENTER);
		pane.getChildren().add(vbox);

		root.getScene().setRoot(pane);
	}

	public Dungeon getDungeon() {
		return this.dungeon;
	}

	@FXML
	void pressPause(ActionEvent event) {
		isPaused = false;
		pause();
	}

	@FXML
	void pressRetry(ActionEvent event) {
		this.restart();
	}

	@FXML
	void pressMenu(ActionEvent event) {
		menuScreen.start();
	}


	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

	public void setNextDungeonScreen(DungeonScreen nextDungeonScreen) {
		this.nextDungeonScreen = nextDungeonScreen;
	}

	public void setPrevDungeonScreen(DungeonScreen prevDungeonScreen) {
		this.prevDungeonScreen = prevDungeonScreen;
	}

	public void switchNextDungeon() {
		this.timeline.stop();
		this.nextDungeonScreen.start(getDungeon());
	}

	public void switchPrevDungeon() {
		this.timeline.stop();
		this.prevDungeonScreen.start(getDungeon());
	}

	public void fightSound() {
		DungeonSoundPlayer.fightSound();
	}

	public void potionSound() {
		DungeonSoundPlayer.potionSound();
	}

	public void doorSound() {
		DungeonSoundPlayer.doorSound();
	}

	public void achieveItemSound() {
		DungeonSoundPlayer.achieveItemSound();
	}

	public void switchFloorSound() {
		DungeonSoundPlayer.switchFloorSound();
	}

	public void explodeSound() {
		DungeonSoundPlayer.explodeSound();
	}

	public void gameOverSound() {
		DungeonSoundPlayer.gameOverSound();
	}

	public void playBGM() {
		DungeonSoundPlayer.playBGM();
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public abstract void restart();

	public DungeonScreen getCurrDungeonScreen() {
		return currDungeonScreen;
	}
}
