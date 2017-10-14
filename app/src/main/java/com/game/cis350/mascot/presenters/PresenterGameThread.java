package com.game.cis350.mascot.presenters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.game.cis350.mascot.interfaces.IImage;
//import com.game.cis350.mascot.views.DrawingPanel;

import java.util.ArrayList;

/**
 * This is the worker thread for the game presenter.
 * @author Reuben, Ariel 10/11/2017
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
     * This is the model that the presenter talks to.
     */
    private IModel model;


    /**
     * This is the constructor for the thread.
     * @param holder handler for the SurfaceView's canvas
     * @param gamePanel reference to our calling class
     */
    PresenterGameThread(){

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
        images = new ArrayList<IImage>();

    }


    @Override
    public void run() {

        while (run) {     //When setRunning(false) occurs, run is
                          //set to false and loop ends, stopping thread

            //TODO: PUT AUTOMATED GAME LOGIC HERE
            //update bus/boat movement, animate all sprites in the game (or figure out which sprites
            //are in the player's view and only update those, but that's an optimization for later)


            ArrayList<IImage> imagesToView = new ArrayList<IImage>();

            // Get coordinates of player
            int playerX = model.getMainPlayer().getX();
            int playerY = model.getMainPlayer().getX();

            for (Collidable currentBus : model.getBusses()) {
                // Bus moves right regardless of player position
                currentBus.setX(currentBus.getX() + 50);

                // Bus moves down as player moves up
                currentBus.setY(currentBus.getY() - model.getMainPlayer().getY());

                // Create new image for each bus
                Image i = new Image(images.get(currentBus.getCurrentFrame()), currentBus.getX(), currentBus.getY());
                imagesToView.add(i);
            }

            // Keep player the same
            Image j = new Image(images.get(model.getMainPlayer().getCurrentFrame()), model.getMainPlayer().getX(), model.getMainPlayer().getY());

            //if anything has moved or animated, pass a new list of all images to the view to display
            //TODO: list update can probably can be optimized

            imagesToView.add(j);
        }
    }
}
