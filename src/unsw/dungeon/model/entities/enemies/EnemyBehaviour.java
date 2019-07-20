package unsw.dungeon.model.entities.enemies;

import unsw.dungeon.model.entities.Player;

public interface EnemyBehaviour {
	public void move(Enemy enemy, Player player);
}
