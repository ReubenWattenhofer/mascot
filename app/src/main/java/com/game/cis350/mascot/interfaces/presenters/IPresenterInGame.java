package com.game.cis350.mascot.interfaces.presenters;

import android.view.MotionEvent;

import com.game.cis350.mascot.interfaces.IImage;

import java.util.ArrayList;

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

    /**
     * This method handles startup when the presenter is created or unpaused.
     */
    void onResume();

    /**
     * This method handles cleanup when the presenter is paused or destroyed.
     */
    void onPause();

    /**
     * This method returns the image list that the view will display.
     * @return list of images
     */
    ArrayList<IImage>[] getLayers();

}
