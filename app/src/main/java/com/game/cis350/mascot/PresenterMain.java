package com.game.cis350.mascot;

import com.game.cis350.mascot.interfaces.MainPresenterInterface;

/**
 * This class handles the main menu logic.
 * @author Reuben 9/24/2017
 */

public class PresenterMain implements MainPresenterInterface {
    /**
     * Uses the view to interact with it.
     */
    private MainActivity view;

    /**
     * Initializes the view.
     * @param v view to assign to presenter
     */
    public PresenterMain(final MainActivity v) {
        view = v;
    }

    @Override
    public void pressedPlay() {
        view.startGame();
    }

    @Override
    public void pressedQuit() {

    }

    @Override
    public void pressedOptions() {

    }
}
