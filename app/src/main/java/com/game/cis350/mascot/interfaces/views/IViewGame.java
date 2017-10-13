package com.game.cis350.mascot.interfaces.views;

import android.content.Context;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.game.cis350.mascot.interfaces.IImage;

import java.util.ArrayList;

/**
 * This interface guarantees functionality for the in-game View.
 * @author Reuben, Ariel 10/12/2017
 */

public interface IViewGame {

    /**
     * This method gets the width of the game view.
     * @return screen width
     */
    int getScreenWidth();

    /**
     * This method gets the height of the game view.
     * @return screen height
     */
    int getScreenHeight();

    /**
     * This method gets the surface view of the game view.
     * @return surface view
     */
    SurfaceView getSurfaceView();

    /**
     * This method gets the surface holder of the game view.
     * @return surface holder
     */
    SurfaceHolder getSurfaceHolder();

    /**
     * This method updates the view with the sprites and positions to draw.
     * @param images images to update view with
     */
    void update(ArrayList<IImage> images);

}
