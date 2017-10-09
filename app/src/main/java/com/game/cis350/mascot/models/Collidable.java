package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.ICollidable;
import com.game.cis350.mascot.interfaces.IDrawable;

import java.util.ArrayList;

/**
 * This class is the basic class for objects that handle collision and movement.
 * @author Reuben 9/20/2017
 */

public class Collidable extends Sprite implements IDrawable,
        ICollidable {

    /**
     * Constructor of the class.
     *
     * @param animation list of sprites to use when animating
     * @param x         x coordinate
     * @param y         y coordinate
     */
    public Collidable(final ArrayList<String> animation, final int x, final int y) {
        super(animation, x, y);
    }

    /**
     * This method moves the object.
     */
    @Override
    public void move() {

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


}
