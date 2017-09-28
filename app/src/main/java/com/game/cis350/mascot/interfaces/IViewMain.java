package com.game.cis350.mascot.interfaces;

/**
 * This interface guarantees functionality for the main menu View.
 * @author Reuben 9/25/2017
 */

public interface IViewMain {
    /**
     * This method starts the game View.
     */
    void startGame();

    /**
     * This method exits the program.
     */
    void quitApp();

    /**
     * This method displays the options menu.
     */
    void showOptions();
}
