package unsw.dungeon.model.entities.movable;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;

import java.util.List;

class Movement {

    private Movable entity;
    private Dungeon dungeon;

    Movement(Movable entity, Dungeon dungeon) {
        this.entity = entity;
        this.dungeon = dungeon;
    }

    public void moveUp() {
        int newX = entity.getX();
        int newY = entity.getY() - 1;
        moveTo(newX, newY);
    }

    public void moveDown() {
        int newX = entity.getX();
        int newY = entity.getY() + 1;
        moveTo(newX, newY);
    }

    public void moveLeft() {
        int newX = entity.getX() - 1;
        int newY = entity.getY();
        moveTo(newX, newY);
    }

    public void moveRight() {
        int newX = entity.getX() + 1;
        int newY = entity.getY();
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


    // mainly for pushing and opening a door
    private void notifyCollision(Movable movable, int target_X, int target_Y) {
        List<Entity> collidedEntities = dungeon.getEntities(target_X, target_Y);
        collidedEntities.forEach(entity -> entity.collideWith((Entity) movable));
    }

}
