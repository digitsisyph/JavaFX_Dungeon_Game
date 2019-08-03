package unsw.dungeon.maker;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple dungeon maker
 *
 * @author Kanadech Jirapongtanavech
 *
 */
public class DungeonMaker {

	private StringProperty[][] cell;
	private int keyID = 0;
	private int doorID = 0;

	public DungeonMaker() {
		cell = new StringProperty[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				cell[i][j] = new SimpleStringProperty();
			}
		}
	}

	public void create() {
		resetID();
		JSONObject obj = new JSONObject();
		obj.put("width", 15);
		obj.put("height", 15);
		JSONArray ent_list = new JSONArray();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (cell[i][j].getValue() == null) {
					continue;
				}
				JSONObject ent = new JSONObject();
				ent.put("x", i);
				ent.put("y", j);
				ent.put("type", cell[i][j].getValue());
				if (cell[i][j].getValue().equals("door")) {
					ent.put("id", doorID);
					doorID++;
				}
				if (cell[i][j].getValue().equals("key")) {
					ent.put("id", keyID);
					keyID++;
				}

				ent_list.put(ent);
			}
		}
		obj.put("entities", ent_list);
		System.out.println(obj.toString(2));
	}

	private void resetID() {
		keyID = 0;
		doorID = 0;
	}
	public StringProperty cellProperty(int x, int y) {
		return cell[x][y];
	}

}
