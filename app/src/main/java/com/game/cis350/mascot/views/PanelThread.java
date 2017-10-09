package com.game.cis350.mascot.views;

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
 * This is the worker thread for drawing content to the game view.
 * @author Reuben 10/7/2017
 */

//Most of this code comes from http://blog.danielnadeau.io/2012/01/android-canvas-beginners-tutorial.html
class PanelThread extends Thread {
    /**
     * This is the handler for the canvas of the SurfaceView we're working with.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * This is the reference to the DrawingPanel we're spawned from.
     */
//    private SurfaceView drawingPanel;

    /**
     * This will kill the thread if set to false.
     */
    private boolean run = false;

    /**
     * Tint to apply to the sprites.
     * credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
     */
    private Paint paint;

    /**
     * The list of images to draw on the screen.
     */
    private ArrayList<IImage> images;

    /**
     * Width and height of the canvas.
     */
//    private int screenX, screenY;
//
//    private Point p;

    /**
     * This is the constructor for the thread.
     * @param surfaceHolder handler for the SurfaceView's canvas
     * @param panel reference to our calling class
     */
    PanelThread(final SurfaceHolder surfaceHolder) { //}, final SurfaceView panel) {
        this.surfaceHolder = surfaceHolder;
//        drawingPanel = panel;

        paint = new Paint();

        images = new ArrayList<IImage>();

//        screenX = 0;
//        screenY = 0;
//
//        p = new Point(0, 0);
    }

    /**
     * This begins and ends the main loop of the thread.
     * @param run to run or not to run, that is the question
     */
    public void setRunning(final boolean run) { //Allow us to stop the thread
        this.run = run;
    }

//    public int getScreenWidth() {
//        return screenX;
//    }
//
//    public int getScreenHeight() {
//        return screenY;
//    }

    /**
     * This method updates the panel with the batch of images to draw.
     * @param images batch of images to draw
     */
    public void update(final ArrayList<IImage> images) {
        this.images = images;
    }

//    public void update(final Point p) {
//        this.p = p;
//    }

    @Override
    public void run() {
        Canvas c;
        while (run) {     //When setRunning(false) occurs, run is
            c = null;      //set to false and loop ends, stopping thread

            //checks if the lockCanvas() method will be success,and if not, will check this statement again
            //credit http://javandroidevelop.blogspot.com/2012/09/all-about-surfaceview-android-lessons.html
            if (!surfaceHolder.getSurface().isValid()){
                continue;
            }

            try {


                c = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

//                    screenX = c.getWidth();
//                    screenY = c.getHeight();

                    // Draw the background color
                    //TODO: change this to black
                    c.drawColor(Color.argb(255, 100, 0, 0));
                    // Choose the brush color for drawing
                    paint.setColor(Color.argb(255,  255, 255, 255));
//
//                    RectF rect = new RectF(p.x, p.y, p.x + 25, p.y + 25);
//                    c.drawRect(rect, paint);

                    for (IImage i : images) {
                        //draw the bitmaps to the canvas
                        //credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
                        c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
//                        c.drawBitmap(i.getImage(), c.getWidth()/ 2- 13, c.getHeight()/ 2 - 13, paint);
                    }

                    //call postInvalidate() instead of onDraw() directly, faster supposedly
//                    drawingPanel.postInvalidate();


                }
            } finally {
                if (c != null) {
                    // do this in a finally so that if an exception is thrown
                    // we don't leave the Surface in an inconsistent state
                    //http://www.mathcs.org/java/android/game_surfaceview.html
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
