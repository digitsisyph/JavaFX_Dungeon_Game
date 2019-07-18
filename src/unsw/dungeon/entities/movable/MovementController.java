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
        if (dungeon.canOccupyGrid(target_X, target_Y)) {
            entity.x().set(target_X);
            entity.y().set(target_Y);
        }
        notifyCollision(entity, target_X, target_Y);
        debuggerIO(entity);
    }


    // mainly for pushing and opening a door
    private void notifyCollision(Movable movable, int target_X, int target_Y) {
        List<Entity> collidedEntities = dungeon.getEntities(target_X, target_Y);
        for (Entity entity : collidedEntities) {
            entity.collideWith((Entity) movable);
        }
    }

    private void debuggerIO(Movable target) {
        for (Entity e : dungeon.getEntities()) {
            if (e != null && e.equals(target))
                System.out.println(e.toString());
        }
    }
}
