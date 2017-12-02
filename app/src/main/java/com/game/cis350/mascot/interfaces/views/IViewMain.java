package com.game.cis350.mascot.interfaces.views;

/**
 * This interface guarantees functionality for the main menu View.
 * @author Reuben 9/25/2017
 */

public interface IViewMain {
    /**
     * This method starts the game View.
     * @param moveType how movement input will be accepted
     */
    void startGame(boolean moveType);

    /**
     * This method displays the credits.
     */
    void showCredits();

    /**
     * Displays the settings.
     * @param s string to set button text to
     */
    void showSettings(String s);

    /**
     * Changes the setting's button text to reflect current setting.
     * @param s string to change button text to
     */
    void changeText(String s);

    /**
     * This method closes the credits.
     */
    void closeCredits();

}
