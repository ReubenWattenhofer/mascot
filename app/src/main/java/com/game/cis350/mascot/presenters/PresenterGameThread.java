package com.game.cis350.mascot.presenters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.game.cis350.mascot.interfaces.IImage;
//import com.game.cis350.mascot.views.DrawingPanel;

import java.util.ArrayList;

/**
 * This is the worker thread for the game presenter.
 * @author Reuben 10/10/2017
 */

//Most of this code comes from http://blog.danielnadeau.io/2012/01/android-canvas-beginners-tutorial.html
class PresenterGameThread extends Thread {

    /**
     * This will kill the thread if set to false.
     */
    private boolean run = false;

    /**
     * The list of images to give to the view.
     */
    private ArrayList<IImage> images;

    /**
     * Width and height of the canvas.
     */


    /**
     * This is the constructor for the thread.
     * @param surfaceHolder handler for the SurfaceView's canvas
     * @param panel reference to our calling class
     */
    GameThread()

        images = new ArrayList<IImage>();
    }

    /**
     * This begins and ends the main loop of the thread.
     * @param run run or stop thread
     */
    public void setRunning(final boolean run) { //Allow us to stop the thread
        this.run = run;
    }


    /**
     * This method is called when the player position is changed.
     */
    public void update(final Point p)
    {
        //Update the game screen by creating a new list of images,
        //centered around the player's new location.

    }


    @Override
    public void run() {

        while (run) {     //When setRunning(false) occurs, run is
                          //set to false and loop ends, stopping thread

            //TODO: PUT AUTOMATED GAME LOGIC HERE
            //update bus/boat movement, animate all sprites in the game (or figure out which sprites
            //are in the player's view and only update those, but that's an optimization for later)

            //if anything has moved or animated, pass a new list of all images to the view to display
            //TODO: list update can probably can be optimized

            }
        }
}
