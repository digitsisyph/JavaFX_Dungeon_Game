package unsw.dungeon.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.goal.Goal;
import unsw.dungeon.model.inventory.Inventory;

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

	private List<ImageView> initialEntities;
	private Dungeon dungeon;
	private DungeonControllerLoader loader;
	private final Timeline timeline;

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		this.dungeon = dungeon;
		this.dungeon.setController(this);
		this.loader = loader;
		this.initialEntities = new ArrayList<>(initialEntities);
		this.timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500),
						e -> {if (!dungeon.isGameOver()) this.dungeon.tick();}));
		timeline.play();
	}

	public GridPane getSquares() {
		return squares;
	}

	public List<ImageView> entImages() {
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
	}

	// TODO set the game info pane
	private void initializeInventoryInfo() {
		Inventory inventory = dungeon.getInventory();

		inventoryInfo.getChildren().add(new Text("Current Inventory:"));

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
		goalInfo.getChildren().add(new Text("Game Goal:"));
		addGoalInfo(goal, goalInfo);
	}

	private void addGoalInfo(Goal goal, Pane pane) {
		VBox subpane = new VBox();
		subpane.setPadding(new Insets(0, 0, 0, 10));
		pane.getChildren().add(subpane);

		CheckBox goal_check = new CheckBox(goal.toString());
		goal.getSatisfiedProperty().addListener((observable, oldValue, newValue) -> {
			goal_check.setSelected(newValue);
		});
		subpane.getChildren().add(goal_check);

		if (!goal.isLeaf()) {
			for (Goal subgoal : goal.getSubgoals())
				addGoalInfo(subgoal, subpane);
		}
	}


	@FXML
	public void handleKeyPress(KeyEvent event) {

		switch (event.getCode()) {
		case UP:
			dungeon.movePlayer(Direction.UP);
			break;
		case DOWN:
			dungeon.movePlayer(Direction.DOWN);
			break;
		case LEFT:
			dungeon.movePlayer(Direction.LEFT);
			break;
		case RIGHT:
			dungeon.movePlayer(Direction.RIGHT);
			break;
		case U:
			dungeon.placeBomb();
			break;
		default:
			break;
		}
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
