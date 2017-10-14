package com.game.cis350.mascot.interfaces.models;

import com.game.cis350.mascot.models.Collidable;

import java.util.ArrayList;


/**
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
}
