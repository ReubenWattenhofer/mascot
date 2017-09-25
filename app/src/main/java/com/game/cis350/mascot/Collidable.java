package com.game.cis350.mascot;

/**
 * This class is the basic class for objects that handle collision and movement.
 * @author Reuben 9/20/2017
 */

class Collidable extends Sprite implements DrawInterface,
        CollideInterface {

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
