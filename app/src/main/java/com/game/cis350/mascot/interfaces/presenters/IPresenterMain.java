package com.game.cis350.mascot.interfaces.presenters;

/**
 * This interface guarantees functionality for the main menu Presenter.
 * @author Reuben 9/25/2017
 */

public interface IPresenterMain {
    /**
     * This method handles the behavior when Play is pressed.
     */
    void pressedPlay();

    /**
     * This method handles the behavior when Credits is pressed.
     */
    void pressedCredits();

    /**
     * Handles behavior when Settings is pressed.
     */
    void pressedSettings();

    /**
     * Handles behavior when style button in settings is pressed.
     */
    void pressedStyleButton();

    /**
     * This method handles the behavior when Close Credits is pressed.
     */
    void pressedLeaveCredits();
}
