package unsw.dungeon.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import unsw.dungeon.Dungeon;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Node node;
    private Dungeon dungeon;
    private Boolean passThrough;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    abstract public void collideWith(Entity entity);

    public void setPassThrough(Boolean bool) {
        this.passThrough = bool;
    }

    public Boolean canPassThrough() {
        return this.passThrough;
    }
    
    public abstract String getImagePath();

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return this.node;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }
    
    public abstract EntityType type();
}
