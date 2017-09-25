package com.game.cis350.mascot;

/**
 * This interface provides guaranteed functionality for Collidable objects.
 * @author Reuben 9/20/2017
 */

interface CollideInterface {

    /**
     * This method holds the logic for movement.
     */
    void move();

    /**
     * This method checks to see if a Collidable object is colliding with
     * another.
     * @param other Collidable object to check against
     * @return true if collision with Collidable object
     */
    boolean collideWith(Collidable other);

}
