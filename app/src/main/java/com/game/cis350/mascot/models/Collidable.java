package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.ICollidable;
import com.game.cis350.mascot.interfaces.models.IDrawable;

/**
 * This class is the basic class for objects that handle collision and movement.
 * @author Reuben 9/20/2017
 */

public class Collidable extends Sprite implements IDrawable,
        ICollidable {

    private CollideTypes collideType;
    private Direction direction;
    /**
     * Constructor of the class.
     *
     * @param animation list of sprites to use when animating
     * @param x         x coordinate
     * @param y         y coordinate
     */
    public Collidable(final String[] animation, final int x, final int y) {
        super(animation, x, y);
        collideType = null;
        direction = null;
    }

    /**
     * This method checks for collision with another Collidable object.
     * @param other Collidable object to check against
     * @return true if collision
     */
    @Override
    public boolean collideWith(final Collidable other) {
        return false;
    }

    @Override
    public void setCollideType(CollideTypes collideType) {
        this.collideType = collideType;
    }

    @Override
    public CollideTypes getCollideType() {
        return collideType;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;

    }

    @Override
    public Direction getDirection() {
        return direction;
    }


}
