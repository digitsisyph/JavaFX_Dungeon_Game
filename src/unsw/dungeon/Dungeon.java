/**
 *
 */
package unsw.dungeon;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    // TODO
    private DungeonController controller;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void setController(DungeonController controller) {
        this.controller = controller;
    }

    public void removeEntity(Entity entity) {
        System.out.println("Remove:" + entity.toString());
        this.entities.remove(entity);
        // TODO
        this.controller.getSquares().getChildren().remove(entity.getNode());
    }

    // helper function: To retrieve an entity in a specific grid
    // if not found, this will return null
    public List<Entity> getEntities(int X, int Y) {
        return entities.stream()
                .filter(entity -> (entity.getX() == X && entity.getY() == Y))
                .collect(Collectors.toList());
    }

    // helper function: check whether a grid is walkable
    public Boolean isWalkable(int X, int Y) {
        return this.getEntities(X, Y).stream()
                .allMatch(Entity::isPassable);
    }
    
    public List<Entity> getEntities(){
    	return entities;
    }
}
