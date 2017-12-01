package com.game.cis350.mascot.interfaces.models;

import com.game.cis350.mascot.models.Collidable;

import java.util.ArrayList;


/**
 * This is the interface for the central model.
 * @author Reuben, Ariel 10/11/2017
 */

public interface IModel {
    /**
     * Gets the main player.
     * @return the main player
     */
    Collidable getMainPlayer();

    /**
     * Gets an arrayList of busses.
     * @return arrayList of busses
     */
    ArrayList<Collidable> getBusses();

    /**
     * Gets an arrayList of boats.
     * @return arrayList of boats
     */
    ArrayList<Collidable> getBoats();

    /**
     * Gets the grid of background sprites.
     * @return grid of background sprites
     */
    IDrawable[][] getBackground();

    /**
     * This method returns the width of the map in tiles.
     * @return width of map in tiles
     */
    int getWidth();

    /**
     * This method returns the height of the map in tiles.
     * @return height of map in tiles
     */
    int getHeight();

    /**
     * Returns the left boundary of the map in tiles.
     * @return left boundary
     */
    int getLeftBoundary();

    /**
     * Returns the right boundary of the map in tiles.
     * @return right boundary
     */
    int getRightBoundary();

    /**
     * Returns the top boundary of the map in tiles.
     * @return top boundary
     */
    int getTopBoundary();

    /**
     * Returns the bottom boundary of the map in tiles.
     * @return bottom boundary
     */
    int getBottomBoundary();
}
