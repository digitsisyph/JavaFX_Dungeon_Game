package unsw.dungeon.model.entities.movable;

import java.util.Random;

public class EnemyMoveClose implements EnemyBehaviour {

	private Random rand = new Random();

	@Override
	public void move(Enemy enemy, Player player) {
		int randInt = rand.nextInt(2);
		if (randInt == 0) {
			if (player.getX() > enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() + 1, enemy.getY())) {
				enemy.moveRight();
			} else if (player.getX() < enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() - 1, enemy.getY())) {
				enemy.moveLeft();
			} else if (player.getY() > enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() + 1)) {
				enemy.moveDown();
			} else if (player.getY() < enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() - 1)) {
				enemy.moveUp();
			}
		} else {
			if (player.getY() > enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() + 1)) {
				enemy.moveDown();
			} else if (player.getY() < enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() - 1)) {
				enemy.moveUp();
			} else if (player.getX() > enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() + 1, enemy.getY())) {
				enemy.moveRight();
			} else if (player.getX() < enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() - 1, enemy.getY())) {
				enemy.moveLeft();
			}
		}
	}

}
