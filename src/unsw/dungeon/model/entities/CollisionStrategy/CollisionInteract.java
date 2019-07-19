package unsw.dungeon.model.entities.CollisionStrategy;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;

class CollisionInteract {
    private Entity entity;

    CollisionInteract(Entity entity) {
        this.entity = entity;
    }

    // nothing should happen
    public void collideWith(Entity collidedEntity, Dungeon dungeon) {;}
}
