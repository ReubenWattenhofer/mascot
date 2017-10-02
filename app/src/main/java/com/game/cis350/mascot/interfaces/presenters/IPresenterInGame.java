package com.game.cis350.mascot.interfaces.presenters;

/**
 * This interface guarantees functionality for the in-game Presenter.
 * @author Reuben 9/25/2017
 */

public interface IPresenterInGame {

    /**
     * This method handles the behavior when the restart option is selected.
     */
    void pressedRestart();

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
