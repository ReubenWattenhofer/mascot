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

    /**
     * Sets speed of object.
     * @param speed speed of object
     */
    void setSpeed(int speed);

    /**
     * Gets speed of object.
     * @return speed of object
     */
    int getSpeed();

    /**
     * Sets number of steps object takes before it stops.
     * @param steps steps to take
     */
    void setSteps(int steps);

    /**
     * Gets number of steps object takes before it stops.
     * @return number of steps
     */
    int getSteps();

    /**
     * Sets step counter.
     * @param counter value to set steps counter.
     */
    void setStepCounter(int counter);

    /**
     * Decrements step counter, setting to 0 if below 0.
     */
    void decrementStepCounter();

    /**
     * Gets step counter.
     * @return step counter
     */
    int getStepCounter();
}
