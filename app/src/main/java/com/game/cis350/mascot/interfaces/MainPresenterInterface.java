package com.game.cis350.mascot.interfaces;

/**
 * This interface guarantees functionality for the main menu Presenter.
 * @author Reuben 9/25/2017
 */

public interface MainPresenterInterface {
    /**
     * This method handles the behavior when Play is pressed.
     */
    void pressedPlay();

    /**
     * This method handles the behavior when Quit is pressed.
     */
    void pressedQuit();

    /**
     * This method handles the behavior when Options is pressed.
     */
    void pressedOptions();
}
