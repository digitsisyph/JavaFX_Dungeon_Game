package unsw.dungeon.model.entities.enemies;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;

public class HumanEnemy extends Enemy implements Movable {

	private EnemyBehaviour moveClose = new EnemyMoveClose();
	private EnemyBehaviour moveAway = new EnemyMoveAway();

	public HumanEnemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setImagePath("/deep_elf_master_archer.png");
		this.setBehaviour(moveClose);
	}

	public void updateStrategy(boolean playerInvincible) {
		if (playerInvincible)
			this.setBehaviour(moveAway);
		else
			this.setBehaviour(moveClose);
	}

}