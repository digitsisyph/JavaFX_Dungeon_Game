package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Door extends Entity {

	private int id;
	private DoorState state;

	public Door(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.id = id;
		this.state = new DoorClosedState(this);
	}

	@Override
	public Boolean canPassThrough() {
		return this.getState().canPassThrough();
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

	public void open() {
		state.nextState();
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().attemptToOpenDoor(this);
	}

	@Override
	public EntityType type() {
		return EntityType.DOOR;
	}

}
