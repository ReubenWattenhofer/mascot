package com.game.cis350.mascot;

/**
 *
 * This interface provides guaranteed functionality for objects that collide with each other.
 * TODO: maybe merge with Collidable class; not sure if this is a proper implementation of an interface
 * @author Reuben
 * 9/20/2017
 */

public interface CollideInterface {

    /**
     * This method holds the logic for movement.
     */
    public void move();

    /**
     * This method checks to see if a Collidable object is colliding with another.
     * @param other Collidable object to check against
     * @return true if collision with Collidable object
     */
    public boolean collideWith(final Collidable other);

}
