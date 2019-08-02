package unsw.dungeon.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import unsw.dungeon.maker.DungeonMaker;
import unsw.dungeon.view.MenuScreen;

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
	
	public void setMenuScreen(MenuScreen screen) {
		this.menu = screen;
	}

	@FXML
	public void initialize() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				TextField t = new TextField();
				t.setManaged(true);
				t.setMaxWidth(60);
				maker.cellProperty(i, j).bindBidirectional(t.textProperty());
				square.add(t, i, j);
			}
		}
	}
}
