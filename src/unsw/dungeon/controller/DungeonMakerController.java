package unsw.dungeon.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import unsw.dungeon.maker.DungeonMaker;
import unsw.dungeon.view.MenuScreen;
import java.util.Arrays;

public class DungeonMakerController {
	@FXML
	private GridPane square;
	@FXML
	private Button backButton;
	@FXML
	private Button createButton;

	private DungeonMaker maker;

	private MenuScreen menu;

	public DungeonMakerController() {
		this.maker = new DungeonMaker();
	}

	@FXML
	public void handleBackButton(ActionEvent event) {
		this.menu.start();
	}

	@FXML
	public void handleCreateButton(ActionEvent event) {
		maker.create();
	}

	@FXML
	public void handleApplyButton(ActionEvent event) {
		System.out.println("Apply action");
	}

	public void setMenuScreen(MenuScreen screen) {
		this.menu = screen;
	}

	@FXML
	public void initialize() {
		String entities[] = { "player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "bomb", "enemy",
				"hound", "invincibility", "wizard", "princess", "up_floor", "down_floor", "stone", "invisible" };
		Arrays.sort(entities);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				ComboBox<String> box = new ComboBox<String>(FXCollections.observableArrayList(entities));
				box.setMaxWidth(90);
				box.setEditable(true);
				box.setPromptText(null);
				box.accessibleTextProperty();
				maker.cellProperty(i, j).bindBidirectional(box.valueProperty());
				square.add(box, i, j);
			}
		}
	}
}
