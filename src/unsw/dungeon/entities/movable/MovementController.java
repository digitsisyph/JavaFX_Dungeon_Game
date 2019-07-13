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
        if (entity.getY() > 0) {
            int newX = entity.getX();
            int newY = entity.getY() - 1;
            if (dungeon.isWalkable(newX, newY))
                entity.y().set(newY);
        }
        notifyCollision(entity);
        debuggerIO(entity);
    }

    public void moveDown(Movable entity) {
        if (entity.getY() < dungeon.getHeight() - 1) {
            int newX = entity.getX();
            int newY = entity.getY() + 1;
            if (dungeon.isWalkable(newX, newY))
                entity.y().set(newY);
        }
        notifyCollision(entity);
        debuggerIO(entity);
    }

    public void moveLeft(Movable entity) {
        if (entity.getX() > 0) {
            int newX = entity.getX() - 1;
            int newY = entity.getY();
            if (dungeon.isWalkable(newX, newY))
                entity.x().set(newX);
        }
        notifyCollision(entity);
        debuggerIO(entity);
    }

    public void moveRight(Movable entity) {
        if (entity.getX() < dungeon.getWidth() - 1) {
            int newX = entity.getX() + 1;
            int newY = entity.getY();
            if (dungeon.isWalkable(newX, newY))
                entity.x().set(newX);
        }
        notifyCollision(entity);
        debuggerIO(entity);
    }

    private void notifyCollision(Movable movable) {
        List<Entity> collidedEntities = dungeon.getEntities(movable.getX(), movable.getY());
        if (movable instanceof Entity) {
            collidedEntities.forEach(entity -> entity.collideWith((Entity) movable));
        }
    }

    private void debuggerIO(Movable target) {
        for (Entity e : dungeon.getEntities()) {
            if (e != null && e.equals(target))
                System.out.println(e.toString());
        }
    }
}
