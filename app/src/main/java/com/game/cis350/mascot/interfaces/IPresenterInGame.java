package com.game.cis350.mascot.interfaces;

/**
 * This interface guarantees functionality for the in-game Presenter.
 * @author Reuben 9/25/2017
 */

public interface IPresenterInGame {

    /**
     * This method handles the behavior when the in-game menu is opened.
     */
    void pressedMenu();

    /**
     * This method handles the behavior when "up" is pressed.
     */
    void pressedUp();

    /**
     * This method handles the behavior when "down" is pressed.
     */
    void pressedDown();

    /**
     * This method handles the behavior when "left" is pressed.
     */
    void pressedLeft();

    /**
     * This method handles the behavior when "right" is pressed.
     */
    void pressedright();

}