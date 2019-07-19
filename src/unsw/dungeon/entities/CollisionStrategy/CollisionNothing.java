package unsw.dungeon.entities.CollisionStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

class CollisionNothing {
    private Entity entity;

    CollisionNothing(Entity entity) {
        this.entity = entity;
    }

    // nothing should happen
    public void collideWith(Entity collidedEntity, Dungeon dungeon) {;}
}
