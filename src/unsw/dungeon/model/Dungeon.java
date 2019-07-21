/**
 *
 */
package unsw.dungeon.model;

import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.ExplodedBomb;
import unsw.dungeon.model.entities.bomb.LitBomb;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemies.Enemy;
import unsw.dungeon.model.goal.Goal;
import unsw.dungeon.model.inventory.Inventory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
	private final int width, height;
	private List<Entity> entities;
	private Player player;
	private Player tempPlayer;
	private Inventory inventory;
	private Goal goal;
	private boolean gameOver = false;

	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.player = null;
		this.tempPlayer = null;
		this.controller = null;
		this.inventory = new Inventory();
		this.goal = null;
	}

	// --- setter ---

	public void setController(DungeonController controller) {
		this.controller = controller;
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.tempPlayer = player;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}


	// --- getter ---

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	// helper function: To retrieve an entity in a specific grid
	public List<Entity> getEntities(int X, int Y) {
		return entities.stream()
				.filter(entity -> (entity.getX() == X && entity.getY() == Y))
				.collect(Collectors.toList());
	}

	public List<Entity> getEntities(EntityType type) {
		return entities.stream()
				.filter(entity -> entity.type() == type)
				.collect(Collectors.toList());
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

	public List<Enemy> getEnemies() {
		return entities.stream()
				.filter(entity -> entity.type() == EntityType.ENEMY)
				.map(Enemy.class::cast)
				.collect(Collectors.toList());
	}

	public List<Switch> getSwitches() {
		return entities.stream()
				.filter(entity -> entity.type() == EntityType.SWITCH)
				.map(Switch.class::cast)
				.collect(Collectors.toList());
	}

	public List<Treasure> getTreasures() {
		return entities.stream()
				.filter(entity -> entity.type() == EntityType.TREASURE)
				.map(Treasure.class::cast)
				.collect(Collectors.toList());
	}

	public List<LitBomb> getLitBombs() {
		return entities.stream()
				.filter(entity -> entity.type() == EntityType.LITBOMB)
				.map(LitBomb.class::cast)
				.collect(Collectors.toList());
	}

	public Inventory getInventory() {
		return inventory;
	}


	// functions for entities

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	// create a new entity in the dungeon
	public void createEntity(Entity entity) {
		System.out.println(entity);
		if (controller != null) {
			controller.addEntityImage(entity);
		}
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		System.out.println("Remove:" + entity.toString());
		// remove it from the dungeon
		this.entities.remove(entity);
		// remove its corresponding ImageView
		if (this.controller != null)
			this.controller.removeEntityImage(entity);
	}


	// player movement

	// helper function: check whether a grid is walkable
	public boolean canOccupyGrid(int X, int Y) {
		return this.getEntities(X, Y).stream().allMatch(Entity::canPassThrough)
				&& (0 <= X && X < this.getWidth() && 0 <= Y && Y < this.getHeight());
	}

	public void movePlayerUp() {
		if (player != null) {
			player.moveUp();
			notifyPerMovement();
		}

	}

	public void movePlayerDown() {
		if (player != null) {
			player.moveDown();
			notifyPerMovement();
		}
	}

	public void movePlayerRight() {
		if (player != null) {
			player.moveRight();
			notifyPerMovement();
		}
	}

	public void movePlayerLeft() {
		if (player != null) {
			player.moveLeft();
			notifyPerMovement();
		}

	}

	/*
	 * TODO For every player movement, something must change Might be better to have
	 * enemy observes player And put invincibility as an attribute inside the player
	 * class
	 */
	private void notifyPerMovement() {
		System.out.println("Player @ " + player.getX() + " " + player.getY());
		if (gameOver) {
			System.out.println("Game Over!");
		} else {
			goalUpdate();
			inventoryUpdate();
			enemyUpdate();
			litbombUpdate();
		}
	}

	private void inventoryUpdate() {
		inventory.updatePerMovement();
	}

	private void enemyUpdate() {
		getEnemies().forEach(enemy -> enemy.updateStrategy(getInventory().isInvincible()));
		getEnemies().forEach(enemy -> enemy.move(tempPlayer));
	}

	private void litbombUpdate() {
		getLitBombs().forEach(bomb -> bomb.nextState());
	}

	private void goalUpdate() {
		if (goal.isSatisfied())
			System.out.println("Goal Achieved!");
	}

	// some retriever functions

	// for picking up

	public void pickUp(Entity entity) {
		switch (entity.type()) {
		case SWORD:
			pickUpSword((Sword) entity);
			break;

		case TREASURE:
			pickUpTreasure((Treasure) entity);
			break;

		case UNLITBOMB:
			pickUpBomb((UnlitBomb) entity);
			break;

		case POTION:
			pickUpPotion((Potion) entity);
			break;

		case KEY:
			pickUpKey((Key) entity);
			break;
		default:
			break;
		}
	}

	// pickUp helper
	private void pickUpSword(Sword sword) {
		removeEntity(sword);
		this.getInventory().pickSword();
	}

	private void pickUpTreasure(Treasure treasure) {
		removeEntity(treasure);
		this.getInventory().pickTreasure();
	}

	private void pickUpBomb(UnlitBomb bomb) {
		removeEntity(bomb);
		this.getInventory().pickBomb();
	}

	private void pickUpPotion(Potion potion) {
		this.getInventory().pickUpPotion();
		removeEntity(potion);
	}

	private void pickUpKey(Key key) {
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

	// --- other interactions

	public void fightEnemy(Enemy enemy) {
		if (this.getInventory().isInvincible()) {
			removeEntity(enemy);
		} else if (this.getInventory().useSword()) {
			removeEntity(enemy);
		} else {
			killPlayer();
		}
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
			System.out.println("Use bomb");
			this.getInventory().useBomb(); // this method just decrease num of bomb by 1;
			createEntity(new LitBomb(player.getX(), player.getY(), this));
		} else {
			System.out.println("No bomb to use");
		}
	}

	// TODO game over
	private void gameOver() {
		gameOver = true;
	}

	public boolean goalAchieved() {
		return this.goal.isSatisfied();
	}

	// a helper function to kill the player
	private void killPlayer() {
		// if the player is invincible now, it would not die
		if (!getInventory().isInvincible()) {
			removeEntity(this.player);
			this.player = null;
			gameOver();
		}
	}

	public void explodeBomb(LitBomb bomb) {
		// put exploded Bomb
		createEntity(new ExplodedBomb(bomb.getX() + 1, bomb.getY(), this));
		createEntity(new ExplodedBomb(bomb.getX() - 1, bomb.getY(), this));
		createEntity(new ExplodedBomb(bomb.getX(), bomb.getY() + 1, this));
		createEntity(new ExplodedBomb(bomb.getX(), bomb.getY() - 1, this));
		// kill nearby Entities
		List<Entity> nearbyEntities = getNearbyEntities(bomb.getX(), bomb.getY());
		for (Entity entity : nearbyEntities) {
			switch (entity.type()) {
			case ENEMY:
				removeEntity(entity);
				break;
			case BOULDER:
				removeEntity(entity);
				break;
			case PLAYER:
				killPlayer();
				break;
			default:
				break;
			}
		}
	}
}
