package unsw.dungeon.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	@FXML
	private GridPane squares;
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
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> this.dungeon.tick()));
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
