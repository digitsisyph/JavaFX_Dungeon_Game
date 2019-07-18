/**
 *
 */
package unsw.dungeon;

import unsw.dungeon.entities.*;
import unsw.dungeon.entities.items.*;
import unsw.dungeon.entities.movable.*;
import unsw.dungeon.goal.*;
import unsw.dungeon.inventory.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

	private DungeonController controller;
	private int width, height;
	private List<Entity> entities;
	private Player player;
	private Inventory inventory;
	private GoalComponent goals;

	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.player = null;
		this.controller = null;
		this.inventory = new Inventory();
		this.goals = null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void setController(DungeonController controller) {
		this.controller = controller;
	}

	public DungeonController getController() {
		return controller;
	}

	public void playerPick(Entity entity) {

	}

	public void removeEntity(Entity entity) {
		System.out.println("Remove:" + entity.toString());
		// remove it from the dungeon
		this.entities.remove(entity);
		// remove its corresponding ImageView
		if (this.controller != null) {
			this.controller.getSquares().getChildren().remove(entity.getNode());
		}
	}

	// helper function: To retrieve an entity in a specific grid
	// if not found, this will return null
	public List<Entity> getEntities(int X, int Y) {
		return entities.stream().filter(entity -> (entity.getX() == X && entity.getY() == Y))
				.collect(Collectors.toList());
	}

	// helper function: check whether a grid is walkable
	public Boolean canOccupyGrid(int X, int Y) {
		return this.getEntities(X, Y).stream().allMatch(Entity::canPassThrough)
				&& (0 <= X && X < this.getWidth() && 0 <= Y && Y < this.getHeight());
	}

	/*
	 * TODO For every player movement, something must change Might be better to have
	 * enemy observes player And put invincibility as an attribute inside the player
	 * class
	 */
	public void playerMovementUpdate() {
		enemyTick();
		bombTick();
		inventoryTick();
		goalTick();
	}

	private void enemyTick() {
		if (getInventory().isInvincible()) {
			getEnemies().forEach(enemy -> enemy.setBehaviour(new MoveAwayFromPlayer()));
		} else {
			getEnemies().forEach(enemy -> enemy.setBehaviour(new MoveTowardsPlayer()));
		}
		getEnemies().forEach(enemy -> enemy.move(this.player));
	}

	private void bombTick() {
		for (Bomb b : getBombs()) {
			if (b.next()) {
				updateGridImage(b, b.getImage());
			}
		}
	}

	public void updateGridImage(Entity ent, Image img) {
		if (this.controller != null) {
			ImageView img1 = (ImageView) ent.getNode();
			img1.setImage(img);
		}
	}

	private void inventoryTick() {
		getInventory().decreaseInvincibility();
	}

	private void goalTick() {
		System.out.println("Goal Achieved: " + goals.satisfied());
	}

	// some retriever functions

	public List<Enemy> getEnemies() {
		return entities.stream().filter(entity -> entity instanceof Enemy).map(Enemy.class::cast)
				.collect(Collectors.toList());
	}

	public List<Switch> getSwitches() {
		return entities.stream().filter(entity -> entity instanceof Switch).map(Switch.class::cast)
				.collect(Collectors.toList());
	}

	public List<Treasure> getTreasures() {
		return entities.stream().filter(entity -> entity instanceof Treasure).map(Treasure.class::cast)
				.collect(Collectors.toList());
	}

	public List<Bomb> getBombs() {
		return entities.stream().filter(entity -> entity instanceof Bomb).map(Bomb.class::cast)
				.collect(Collectors.toList());
	}

	public List<Entity> getEntityOfType(EntityType type) {
		return entities.stream().filter(entity -> entity.type().equals(type)).collect(Collectors.toList());
	}

	public Inventory getInventory() {
		return inventory;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setGoal(GoalComponent goal) {
		this.goals = goal;
	}

	// for interacting

	public void pickUpSword(Sword sword) {
		removeEntity(sword);
		this.getInventory().pickSword();
		// TODO debug
		this.getInventory().debug();
	}

	public void pickUpTreasure(Treasure treasure) {
		removeEntity(treasure);
		this.getInventory().pickTreasure();
		// TODO debug
		this.getInventory().debug();
	}

	public void pickUpBomb(Bomb bomb) {
		removeEntity(bomb);
		this.getInventory().pickBomb();
		// TODO debug
		this.getInventory().debug();
	}

	public void fightEnemy(Enemy enemy) {
		if (this.getInventory().isInvincible()) {
			removeEntity(enemy);
		} else if (this.getInventory().useSword()) {
			removeEntity(enemy);
		} else {
			// TODO player die
			// this is broken right now
			removeEntity(player);
		}
	}

	public void pickUpKey(Key key) {
		// player can only have 1 key
		if (this.getInventory().getKey() == null) {
			this.getInventory().pickKey(key.getId());
			removeEntity(key);
			System.out.println("Key picked up");
		} else {
			System.out.println("Already has a key");
		}
		this.getInventory().debug();
	}

	public void attemptToOpenDoor(Door door) {
		if (!door.canPassThrough()) {
			if (this.getInventory().getKey() != null) {
				if (this.getInventory().getKeyID() == door.getId()) {
					this.getInventory().useKey();
					door.open();
				} else {
					System.out.println("Wrong key");
				}
			} else {
				System.out.println("No key");
			}
		}
	}

	public void playerPlacesBomb() {
		if (this.getInventory().getBombNum() > 0) {
			this.getInventory().useBomb(); // this method just decrease numbomb by 1;
			Bomb newBomb = new Bomb(player.getX(), player.getY(), this);
			newBomb.lit();
			// add into dungeon entity list
			addEntity(newBomb);
			// add imageview to gridpane
			ImageView bombImg = new ImageView(newBomb.getImage());
			newBomb.setNode(bombImg);
			GridPane.setColumnIndex(bombImg, newBomb.getX());
			GridPane.setRowIndex(bombImg, newBomb.getY());
			this.controller.getSquares().getChildren().add(bombImg);
			System.out.println("Use bomb");
		} else {
			System.out.println("No bomb to use");
		}
	}

	public void pickUpPotion(Potion potion) {
		this.getInventory().pickUpPotion();
		removeEntity(potion);
	}

	// a helper function for bomb
	private List<Entity> getNearbyEntities(int X, int Y) {
		List<Entity> nearbyEntities = new LinkedList<Entity>();
		nearbyEntities.addAll(getEntities(X, Y + 1));
		nearbyEntities.addAll(getEntities(X, Y - 1));
		nearbyEntities.addAll(getEntities(X + 1, Y));
		nearbyEntities.addAll(getEntities(X - 1, Y));
		return nearbyEntities;
	}

	// TODO game over
	private void gameOver() {
		//
	}

	// a helper function to kill the player
	private void killPlayer() {
		// if the player is invincible now, it would not die
		if (getInventory().isInvincible())
			return;
		else {
			removeEntity(player);
			gameOver();
		}

	}

	public void bombActivated(Bomb bomb) {
		List<Entity> nearbyEntities = getNearbyEntities(bomb.getX(), bomb.getY());
		for (Entity entity : nearbyEntities) {
			if (entity instanceof Player) {
				killPlayer();
			} else if (entity instanceof Enemy || entity instanceof Boulder) {
				removeEntity(entity);
			}
		}
		removeEntity(bomb);
	}
}
