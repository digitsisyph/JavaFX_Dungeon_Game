package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.movable.Player;

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

	private List<ImageView> initialEntities;

	private Player player;
	private Dungeon dungeon;

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
		this.dungeon = dungeon;
		this.dungeon.setController(this);
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
	}

	public GridPane getSquares() {
		return squares;
	}
	
	public List<ImageView> entImages(){
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
			player.moveUp();
			break;
		case DOWN:
			player.moveDown();
			break;
		case LEFT:
			player.moveLeft();
			break;
		case RIGHT:
			player.moveRight();
			break;
		case U:
			player.placeBomb();
		default:
			break;
		}
	}

}
