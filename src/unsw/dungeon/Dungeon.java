/**
 *
 */
package unsw.dungeon;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.items.Bomb;
import unsw.dungeon.entities.items.Switch;
import unsw.dungeon.entities.items.Sword;
import unsw.dungeon.entities.items.Treasure;
import unsw.dungeon.entities.movable.Enemy;
import unsw.dungeon.entities.movable.Player;
import unsw.dungeon.goal.*;
import unsw.dungeon.inventory.*;

import java.util.ArrayList;
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


	public void playerPick(Entity entity) {

	}


	public void removeEntity(Entity entity) {
		System.out.println("Remove:" + entity.toString());
		// remove it from the dungeon
		this.entities.remove(entity);
		// remove its correspoding ImageView
		this.controller.getSquares().getChildren().remove(entity.getNode());
	}

	// helper function: To retrieve an entity in a specific grid
	// if not found, this will return null
	public List<Entity> getEntities(int X, int Y) {
		return entities.stream().filter(entity -> (entity.getX() == X && entity.getY() == Y))
				.collect(Collectors.toList());
	}

	// helper function: check whether a grid is walkable
	public Boolean isWalkable(int X, int Y) {
		return this.getEntities(X, Y).stream()
				.allMatch(Entity::isPassable)
				&& (0 <= X && X < this.getWidth() && 0 <= Y && Y < this.getHeight());
	}

	public void playerMovementUpdate() {
		getEnemies().forEach(enemy -> enemy.moveTowardsPlayer(this.player));
		System.out.println("Goal Achieved: " + goals.satisfied());
	}

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

	public void pickSword(Sword sword) {
		removeEntity(sword);
		this.getInventory().pickSword();
		// TODO debug
		this.getInventory().debug();
	}

	public void pickTreasure(Treasure treasure) {
		removeEntity(treasure);
		this.getInventory().pickTreasure();
		// TODO debug
		this.getInventory().debug();
	}

	public void pickBomb(Bomb bomb) {
		removeEntity(bomb);
		this.getInventory().pickBomb();
		// TODO debug
		this.getInventory().debug();
	}

	public void fightEnemy(Enemy enemy) {
		if (this.getInventory().useSword()) {
			removeEntity(enemy);
		} else {
			// TODO player die
			removeEntity(player);
		}
	}

}
