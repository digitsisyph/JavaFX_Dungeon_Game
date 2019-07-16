package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Door extends Entity {

	private int id;
	private DoorState closedDoor;
	private DoorState openedDoor;
	private DoorState state;

	public Door(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		
		closedDoor = new ClosedDoorState(this);
		openedDoor = new OpenedDoorState(this);
		this.state = closedDoor;
		this.id = id;
		this.setPassThrough(state.passThrough());
	}

	@Override
	public Image getImage() {
		return state.getImage();
	}

	/**
	 * @return the state
	 */
	public DoorState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(DoorState state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the closedDoor
	 */
	public DoorState getClosedDoor() {
		return closedDoor;
	}

	/**
	 * @return the openedDoor
	 */
	public DoorState getOpenedDoor() {
		return openedDoor;
	}

	public void open() {
		state.unlock();
		this.setPassThrough(state.passThrough());
		if(this.getNode() instanceof ImageView) {
			System.out.println("YESSSS");
			ImageView img = (ImageView)this.getNode();
			Image img2 = state.getImage();
			img.setImage(img2);
		}
		if(this.getDungeon().getController().entImages().contains(this.getNode())) {
			System.out.println("YESSSS");
		}
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().attemptToOpenDoor(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.DOOR;
	}

}
