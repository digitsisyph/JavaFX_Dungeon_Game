package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

import java.util.LinkedList;

public class EnemyMoveClose implements EnemyBehaviour {

	// a helper class for path finding
	class Grid {
		final int x;
		final int y;
		Grid(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// use BFS to find next grid
	private Grid findNextGrid(Enemy enemy, Player player, Dungeon dungeon) {

		Grid[][] visited = new Grid[dungeon.getWidth()][dungeon.getHeight()];
		boolean found = false;
		LinkedList<Grid> queue = new LinkedList<Grid>();

		Grid enemy_grid = new Grid(enemy.getX(), enemy.getY());
		visited[enemy.getX()][enemy.getY()] = enemy_grid;
		queue.add(enemy_grid);
		Grid curr = enemy_grid;

		// try to find the player
		while (queue.size() != 0 && !found)
		{
			curr = queue.poll();
			Grid[] nearbyGrids = new Grid[] {
					new Grid(curr.x + 1, curr.y),
					new Grid(curr.x - 1, curr.y),
					new Grid(curr.x, curr.y + 1),
					new Grid(curr.x, curr.y - 1)
			};
			for (Grid grid : nearbyGrids) {
				if (grid.x == player.getX() && grid.y == player.getY()) {
					found = true;
					// this is to let enemy attack player
					visited[grid.x][grid.y] = curr;
					curr = grid;
					break;
				} else if (visited[grid.x][grid.y] == null
						&& dungeon.canOccupyGrid(grid.x, grid.y)) {
					visited[grid.x][grid.y] = curr;
					queue.add(grid);
				}
			}
		}

		// retrieve the path
		while (visited[curr.x][curr.y] != enemy_grid)
			curr = visited[curr.x][curr.y];
		return curr;
	}

	@Override
	public void move(Enemy enemy, Player player) {
		Grid next_grid = findNextGrid(enemy, player, enemy.getDungeon());
		System.out.println("moveTo " + next_grid.x + " " + next_grid.y);

		if (next_grid.x > enemy.getX()) {
			enemy.moveRight();
		} else if (next_grid.x < enemy.getX()) {
			enemy.moveLeft();
		} else if (next_grid.y > enemy.getY()) {
			enemy.moveDown();
		} else if (next_grid.y < enemy.getY()) {
			enemy.moveUp();
		}
	}
}
