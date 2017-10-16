package com.game.cis350.mascot.interfaces.views;

/**
 * This interface guarantees functionality for the in-game View.
 * @author Reuben 10/12/2017
 */

public interface IViewGame {

    /**
     * This method gets the width of the game view.
     * @return screen width
     */
    int getScreenWidth();

    /**
     * This method gets the height of the game view.
     * @return screen height
     */
    int getScreenHeight();

    /**
     * This method restarts the game.
     */
    void restart();

}
