package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.ICollidable;
import com.game.cis350.mascot.interfaces.models.IDrawable;

import java.util.Objects;

/**
 * This class is the basic class for objects that handle collision and movement.
 * @author Reuben 9/20/2017
 */

public class Collidable extends Sprite implements IDrawable,
        ICollidable {

    /**
     * Collision type of the object.
     */
//    private CollideTypes collideType;

    /**
     * Direction of the object's movement.
     */
    private Direction direction;
    /**
     * Speed of the objects movement.
     */
    private int speed;
    /**
     * Steps before the object stops, use -1 to disregard.
     */
    private int steps, stepCounter;

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
        speed = 0;
        steps = -1;
        stepCounter = 0;
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

    //@Override
    //public void setCollideType(final CollideTypes collideType) {
      //  this.collideType = collideType;
    //}

    //@Override
    //public CollideTypes getCollideType() {
     //   return collideType;
    //}

    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;

    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSteps(final int steps) {
        this.steps = steps;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public void setStepCounter(final int counter) {
        stepCounter = counter;
    }

    @Override
    public void decrementStepCounter() {
        stepCounter = (stepCounter <= 0) ? 0 : stepCounter - 1;
    }

    @Override
    public int getStepCounter() {
        return stepCounter;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Sprite)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Collidable collidable = (Collidable) o;
        return  direction == collidable.direction
                && stepCounter == collidable.stepCounter
                && steps == collidable.steps
                && speed == collidable.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animation, frame, maxFrame, x, y, direction, stepCounter, stepCounter, steps);
    }

}
