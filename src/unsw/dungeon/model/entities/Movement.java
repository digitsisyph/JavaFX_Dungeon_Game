package unsw.dungeon.model.entities;

import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;

import java.util.List;

public class Movement {

    private Movable entity;
    private Dungeon dungeon;

    public Movement(Movable entity, Dungeon dungeon) {
        this.entity = entity;
        this.dungeon = dungeon;
    }

    public void move(Direction direction) {
        int newX = direction.newX(entity.getX());
        int newY = direction.newY(entity.getY());
        moveTo(newX, newY);
    }

    // helper function for moving up, down, left, right
    private void moveTo(int target_X, int target_Y) {
        if (dungeon.canOccupyGrid(target_X, target_Y)) {
            entity.x().set(target_X);
            entity.y().set(target_Y);
        }
        // after a movement, no matter success or fails, there would be a collision
        notifyCollision(entity, target_X, target_Y);
    }

    // a movement would produce a collision to the target grid
    private void notifyCollision(Movable movable, int target_X, int target_Y) {
        List<Entity> collidedEntities = dungeon.getEntities(target_X, target_Y);
        collidedEntities.forEach(entity -> entity.collideWith((Entity) movable));
    }

}
