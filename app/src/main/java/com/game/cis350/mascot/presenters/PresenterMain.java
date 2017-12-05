package com.game.cis350.mascot.presenters;

import com.game.cis350.mascot.interfaces.presenters.IPresenterMain;
import com.game.cis350.mascot.interfaces.views.IViewMain;
import com.game.cis350.mascot.views.MainActivity;

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
     * Decides how movement is inputted.
     */
    private boolean tapToMove;

    /**
     * Initializes the view.
     * @param v view to assign to presenter
     */
    public PresenterMain(final MainActivity v) {
        view = v;
        tapToMove = true;
    }

    @Override
    public void pressedPlay() {
        view.startGame(tapToMove);
    }

    @Override
    public void pressedCredits() {
        view.showCredits();
    }

    @Override
    public void pressedSettings() {
        //set style button text to reflect current setting
        String s = "Tap to\nmove";
        if (!tapToMove) {
            s = "Swipe to\nmove";
        }

        view.showSettings(s);
    }

    @Override
    public void pressedStyleButton() {
        //switch setting
        tapToMove = !tapToMove;
        //set style button text to reflect current setting
        String s = "Tap to\nmove";
        if (!tapToMove) {
            s = "Swipe to\nmove";
        }

        view.changeText(s);

    }

    @Override
    public void pressedLeaveCredits() {
        view.closeCredits();
    }
}
