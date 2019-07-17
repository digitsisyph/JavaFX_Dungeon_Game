package unsw.dungeon.entities.movable;

public class MoveAwayFromPlayer implements MovementBehaviour {

	@Override
	public void move(Enemy enemy, Player player) {
		// TODO Right now he is only trying to move left
		enemy.moveLeft();
	}

}
