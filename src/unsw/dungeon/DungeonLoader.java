package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.entities.*;
import unsw.dungeon.entities.items.*;
import unsw.dungeon.entities.movable.Boulder;
import unsw.dungeon.entities.movable.Enemy;
import unsw.dungeon.entities.movable.Player;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * 
	 * @return
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);
		GoalComponent base = new ANDGoals(); // base goals
		JSONArray jsonEntities = json.getJSONArray("entities");
		JSONObject goalConditions = json.getJSONObject("goal-condition");
		System.out.println(goalConditions.toString());
		loadGoal(dungeon, goalConditions, base);

		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}
		dungeon.setGoal(base); // give dungeon base goals
		base.print();
		return dungeon;
	}

	private void loadGoal(Dungeon dungeon, JSONObject json, GoalComponent base) {
		String type = json.getString("goal");
		switch (type) {
		case "exit":
			base.add(new ExitGoal(dungeon));
			break;
		case "enemies":
			base.add(new EnemyGoal(dungeon));
			break;
		case "boulders":
			base.add(new BoulderGoal(dungeon));
			break;
		case "treasure":
			base.add(new TreasureGoal(dungeon));
			break;
		case "AND":
			ANDGoals childAND = new ANDGoals();
			base.add(childAND);
			JSONArray subGoals = json.getJSONArray("subgoals");
			for(int i = 0; i < subGoals.length(); i++) {
				loadGoal(dungeon,subGoals.getJSONObject(i),childAND);
			}
			break;
		case "OR":
			ORGoals childOR = new ORGoals();
			base.add(childOR);
			JSONArray subGoals1 = json.getJSONArray("subgoals");
			for(int i = 0; i < subGoals1.length(); i++) {
				loadGoal(dungeon,subGoals1.getJSONObject(i),childOR);
			}
			break;
		}
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(x, y, dungeon);
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(x, y, dungeon);
			onLoad(wall);
			entity = wall;
			break;
		case "exit":
			Exit exit = new Exit(x, y, dungeon);
			onLoad(exit);
			entity = exit;
			break;
		case "treasure":
			Treasure treasure = new Treasure(x, y, dungeon);
			onLoad(treasure);
			entity = treasure;
			break;
		case "door":
			Door door = new Door(x, y, dungeon);
			onLoad(door);
			entity = door;
			break;
		case "key":
			Key key = new Key(x, y, dungeon);
			onLoad(key);
			entity = key;
			break;
		case "boulder":
			Boulder boulder = new Boulder(x, y, dungeon);
			onLoad(boulder);
			entity = boulder;
			break;
		case "switch":
			Switch plate = new Switch(x, y, null);
			onLoad(plate);
			entity = plate;
			break;
		case "bomb":
			Bomb bomb = new Bomb(x, y, dungeon);
			onLoad(bomb);
			entity = bomb;
			break;
		case "enemy":
			Enemy enemy = new Enemy(x, y, dungeon);
			onLoad(enemy);
			entity = enemy;
			break;
		case "sword":
			Sword sword = new Sword(x, y, dungeon);
			onLoad(sword);
			entity = sword;
			break;
		case "invincibility":
			Potion potion = new Potion(x, y, dungeon);
			onLoad(potion);
			entity = potion;
			break;
		}
		dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity entity);

	// TODO Create additional abstract methods for the other entities

}
