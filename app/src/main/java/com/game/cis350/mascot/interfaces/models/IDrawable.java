package com.game.cis350.mascot.interfaces.models;

import com.game.cis350.mascot.models.CollideTypes;

/**
 * This interface provides guaranteed functionality for Draw objects.
 * @author Reuben 9/20/2017.
 */

public interface IDrawable {

    /**
     * This method updates the animation frame of the object
     * (ie from sprite 1 to sprite 2).
     */
    void incrementFrame();

    /**
     * This method returns the animation frame that should be displayed.
     * @return current animation frame
     */
    String getCurrentFrame();

    /**
     * This method gets all the frames of the object.
     * @return animation frames of object
     */
    String[] getFrames();

    /**
     * This method sets the frames of the object.
     * @param frames animation frames
     */
    void setFrames(String[] frames);

    /**
     * This method sets the x coordinate of the object.
     * @param x x coordinate
     */
    void setX(int x);

    /**
     * This method sets the y coordinate of the object.
     * @param y y coordinate
     */
    void setY(int y);

    /**
     * This method gets the x coordinate of the object.
     * @return x
     */
    int getX();

    /**
     * This method gets the y coordinate of the object.
     * @return y
     */
    int getY();

    /**
     * Sets the collide type of the object.
     * @param collideType collide type
     */
    void setCollideType(CollideTypes collideType);

    /**
     * Gets the collide type of the object.
     * @return collide type
     */
    CollideTypes getCollideType();
}
