package unsw.dungeon.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.goal.Goal;
import unsw.dungeon.model.inventory.Inventory;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

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

	private MenuScreen menuScreen;
	private DungeonScreen nextDungeonScreen;
	private DungeonScreen prevDungeonScreen;

	private boolean isPaused = false;

	private List<ImageView> initialEntities;
	private Dungeon dungeon;
	private DungeonControllerLoader loader;
	private Timeline timeline;


	public DungeonController() {

	}

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		this.dungeon = dungeon;
		this.dungeon.setController(this);
		this.loader = loader;
		this.initialEntities = new ArrayList<>(initialEntities);

		// timeline
		this.timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500),
						e -> {if (!dungeon.isGameOver()) this.dungeon.tick();}));
		timeline.play();
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

	public GridPane getSquares() {
		return squares;
	}

	public List<ImageView> getImages() {
		return initialEntities;
	}

	@FXML
	public void initialize() {
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

		initializeInventoryInfo();
		initializeGoalInfo();
		gameInfo.setSpacing(50);

		// timeline
		this.timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500),
						e -> {if (!dungeon.isGameOver()) this.dungeon.tick();}));
		timeline.play();
	}

	// TODO set the game info pane
	private void initializeInventoryInfo() {
		Inventory inventory = dungeon.getInventory();

		Text title = new Text("Current Inventory:");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		inventoryInfo.getChildren().add(title);

		// bomb
		Text bombInfo = new Text("- Bomb num: " + 0);
		bombInfo.setVisible(false);		// invisible at first
		inventory.getBombNumProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((int) newValue > 0) {
					bombInfo.setVisible(true);
					bombInfo.setText("- Bomb num: " + newValue);
				} else
					bombInfo.setVisible(false);
			}
		});

		// sword
		Text swordInfo = new Text();
		swordInfo.setVisible(false);	// invisible at first
		inventory.getSwordDurabilityProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((int) newValue > 0) {
					swordInfo.setVisible(true);
					swordInfo.setText("- Sword Durability: " + newValue);
				} else
					swordInfo.setVisible(false);
			}
		});

		// TODO key

		// invincible
		Text invincibleInfo = new Text();
		invincibleInfo.setVisible(false);	// invisible at first
		inventory.getInvincibleRemainingProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((int) newValue > 0) {
					invincibleInfo.setVisible(true);
					invincibleInfo.setText("- Remaining Invincible Time: " + newValue);
				} else
					invincibleInfo.setVisible(false);
			}
		});

		inventoryInfo.getChildren().addAll(bombInfo, swordInfo, invincibleInfo);
	}

	private void initializeGoalInfo() {
		Goal goal = dungeon.getGoal();

		Text title = new Text("Game Goal:");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		goalInfo.getChildren().add(title);

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

	private void darkleScreen() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.8);
		root.setEffect(colorAdjust);
	}

	private void lightenScreen() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(0);
		root.setEffect(colorAdjust);
	}


	// game over
	public void gameOver(String gameOverInfo) {
		timeline.stop();

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: #000000");


		Text info = new Text("Game Over \n\n" + gameOverInfo + "\n\n");
		info.setTextAlignment(TextAlignment.CENTER);
		info.setFont(new Font(18));
		info.setFill(Color.ALICEBLUE);

		Button backButton = new Button("Back to Menu");
		backButton.setPadding(new Insets(10, 10, 10, 10));
		backButton.setOnAction(event -> menuScreen.start());

		//Button retryButton = new Button("Retry");
		//retryButton.setPadding(new Insets(10, 10, 10, 10));
		//retryButton.setOnAction(event -> {
		//	loader.loadController(this);
		//	pane.getScene().setRoot(root);
		//	this.squares.getChildren().clear();
		//	this.initialize();
		//});

		VBox vbox = new VBox();
		vbox.getChildren().addAll(info, backButton);
		vbox.setAlignment(Pos.CENTER);
		pane.getChildren().add(vbox);

		root.getScene().setRoot(pane);
	}

	public Dungeon getDungeon() {
		return this.dungeon;
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
		try {
			this.nextDungeonScreen.load("advanced2.json");
			this.nextDungeonScreen.start(getDungeon().getInventory());
			this.timeline.stop();
		} catch (IOException e) {
			System.out.println("no such file!");
		}
	}

	public void switchPrevDungeon() {
		this.prevDungeonScreen.start();
	}

	public void start() {
		this.timeline.play();
	}


	// TODO maybe change?
	public void removeEntityImage(Entity entity) {
		this.getSquares().getChildren().remove(entity.getNode());
	}

	public void addEntityImage(Entity entity) {
		this.loader.onLoad(entity);
		this.getSquares().getChildren().add(entity.getNode());
	}

}
