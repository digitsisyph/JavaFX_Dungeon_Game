package unsw.dungeon.model.entities.movable;

import javafx.beans.property.IntegerProperty;

public interface Movable {

    public IntegerProperty x();
    public IntegerProperty y();

    public int getX();
    public int getY();

    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
}
