package unsw.dungeon.model.entities.movable;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;

import java.util.List;

class Movement {

    private Dungeon dungeon;

    Movement(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void moveUp(Movable entity) {
        int newX = entity.getX();
        int newY = entity.getY() - 1;
        moveTo(entity, newX, newY);
    }

    public void moveDown(Movable entity) {
        int newX = entity.getX();
        int newY = entity.getY() + 1;
        moveTo(entity, newX, newY);
    }

    public void moveLeft(Movable entity) {
        int newX = entity.getX() - 1;
        int newY = entity.getY();
        moveTo(entity, newX, newY);
    }

    public void moveRight(Movable entity) {
        int newX = entity.getX() + 1;
        int newY = entity.getY();
        moveTo(entity, newX, newY);
    }

    // helper function for moving up, down, left, right
    private void moveTo(Movable entity, int target_X, int target_Y) {
        if (dungeon.canOccupyGrid(target_X, target_Y)) {
            entity.x().set(target_X);
            entity.y().set(target_Y);
        }
        // after a movement, no matter success or fails, there would be a collision
        notifyCollision(entity, target_X, target_Y);
    }


    // mainly for pushing and opening a door
    private void notifyCollision(Movable movable, int target_X, int target_Y) {
        List<Entity> collidedEntities = dungeon.getEntities(target_X, target_Y);
        collidedEntities.forEach(entity -> entity.collideWith((Entity) movable));
    }

}
