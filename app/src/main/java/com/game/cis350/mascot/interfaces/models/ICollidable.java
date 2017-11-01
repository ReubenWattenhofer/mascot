package com.game.cis350.mascot.interfaces.models;

import com.game.cis350.mascot.models.Collidable;
import com.game.cis350.mascot.models.CollideTypes;
import com.game.cis350.mascot.models.Direction;

/**
 * This interface provides guaranteed functionality for Collidable objects.
 * @author Reuben 9/20/2017
 */

public interface ICollidable {

    /**
     * This method checks to see if a Collidable object is colliding with
     * another.
     * @param other Collidable object to check against
     * @return true if collision with Collidable object
     */
    boolean collideWith(Collidable other);

    //TODO: write javadocs
    void setCollideType(CollideTypes collideType);
    CollideTypes getCollideType();
    void setDirection(Direction direction);
    Direction getDirection();
}
