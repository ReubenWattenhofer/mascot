package com.game.cis350.mascot;

import com.game.cis350.mascot.interfaces.IPresenterMain;
import com.game.cis350.mascot.interfaces.IViewMain;

/**
 * This class handles the main menu logic.
 * @author Reuben 9/24/2017
 */

public class PresenterMain implements IPresenterMain {
    /**
     * Uses the view to interact with it.
     */
    private IViewMain view;

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
    public void pressedOptions() {

    }
}
