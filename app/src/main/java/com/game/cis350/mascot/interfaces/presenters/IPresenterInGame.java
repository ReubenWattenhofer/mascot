package com.game.cis350.mascot.interfaces.presenters;

import android.view.MotionEvent;

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
     * This method processes the user input.
     * @param event determines what kind of input occurred
     */
    void pressedScreen(MotionEvent event);

}
