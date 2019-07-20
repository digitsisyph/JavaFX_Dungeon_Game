package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemies.HoundEnemy;
import unsw.dungeon.model.entities.enemies.HumanEnemy;
import unsw.dungeon.model.goal.*;
import unsw.dungeon.model.Dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
		Goal base = new AndGoals(); // base goals
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

	// add goals for the dungeon
	private void loadGoal(Dungeon dungeon, JSONObject json, Goal base) {
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
				AndGoals childAND = new AndGoals();
				base.add(childAND);
				JSONArray subGoals = json.getJSONArray("subgoals");
				for (int i = 0; i < subGoals.length(); i++) {
					loadGoal(dungeon, subGoals.getJSONObject(i), childAND);
				}
				break;
			case "OR":
				OrGoals childOR = new OrGoals();
				base.add(childOR);
				JSONArray subGoals1 = json.getJSONArray("subgoals");
				for (int i = 0; i < subGoals1.length(); i++) {
					loadGoal(dungeon, subGoals1.getJSONObject(i), childOR);
				}
				break;
		}
	}

	// add entities for the dungeon
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
				entity = new Wall(x, y, dungeon);
				onLoad(entity);
				break;
			case "exit":
				entity = new Exit(x, y, dungeon);
				onLoad(entity);
				break;
			case "treasure":
				entity = new Treasure(x, y, dungeon);
				onLoad(entity);
				break;
			case "door":
				entity = new Door(x, y, dungeon, json.getInt("id"));
				onLoad(entity);
				break;
			case "key":
				entity = new Key(x, y, dungeon, json.getInt("id"));
				onLoad(entity);
				break;
			case "boulder":
				entity = new Boulder(x, y, dungeon);
				onLoad(entity);
				break;
			case "switch":
				entity = new Switch(x, y, dungeon);
				onLoad(entity);
				break;
			case "bomb":
				entity = new UnlitBomb(x, y, dungeon);
				onLoad(entity);
				break;
			case "enemy":
				entity = new HumanEnemy(x, y, dungeon);
				onLoad(entity);
				break;
			case "hound":
				entity = new HoundEnemy(x, y, dungeon);
				onLoad(entity);
				break;
			case "sword":
				entity = new Sword(x, y, dungeon);
				onLoad(entity);
				break;
			case "invincibility":
				entity = new Potion(x, y, dungeon);
				onLoad(entity);
				break;
		}
		if (entity != null)
			dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity entity);
}
