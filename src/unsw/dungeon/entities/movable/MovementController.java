package unsw.dungeon.entities.movable;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

import java.util.List;

class MovementController {

    private Dungeon dungeon;

    MovementController(Dungeon dungeon) {
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
        if (target_X < 0 || target_X >= dungeon.getWidth()
            || target_Y < 0 || target_Y >= dungeon.getHeight())
            return;
        if (dungeon.isWalkable(target_X, target_Y)) {
            entity.x().set(target_X);
            entity.y().set(target_Y);
            notifyCollision(entity);
        } else {
            notifyCollisionFrom(entity, target_X, target_Y);
        }
        debuggerIO(entity);
    }


    // notify a collision
    private void notifyCollision(Movable movable) {
        List<Entity> collidedEntities = dungeon.getEntities(movable.getX(), movable.getY());
        if (movable instanceof Entity) {
            collidedEntities.forEach(entity -> entity.collideWith((Entity) movable));
        }
    }

    // mainly for pushing and opening a door
    private void notifyCollisionFrom(Movable movable, int target_X, int target_Y) {
        List<Entity> collidedEntities = dungeon.getEntities(target_X, target_Y);
        for (Entity entity : collidedEntities) {
            if (entity instanceof Boulder && movable instanceof Entity) {
                ((Boulder) entity).bePushed((Entity) movable);
            }
        }
    }

    private void debuggerIO(Movable target) {
        for (Entity e : dungeon.getEntities()) {
            if (e != null && e.equals(target))
                System.out.println(e.toString());
        }
    }
}
