package com.game.cis350.mascot.presenters;

import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;

/**
 * This class handles the in-game logic.
 * @author Reuben 9/25/2017
 */

public class PresenterInGame implements IPresenterInGame {

    /**
     * Uses the view to interact with it.
     */
    private IViewGame view;

    /**
     * Initializes the view.
     * @param v view to assign to presenter
     */
    public PresenterInGame(final IViewGame v) {
        view = v;
    }

    @Override
    public void pressedRestart() {

    }

    @Override
    public void pressedUp() {

    }

    @Override
    public void pressedDown() {

    }

    @Override
    public void pressedLeft() {

    }

    @Override
    public void pressedright() {

    }
}
