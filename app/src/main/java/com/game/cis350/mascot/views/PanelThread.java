package com.game.cis350.mascot.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

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
     * The list of layer1 to draw on the screen.
     */
    //read about volatile here http://tutorials.jenkov.com/java-concurrency/volatile.html
//    private volatile HashMap<Object, IImage> layer1;
    private ArrayList<IImage>[] layers;

    /**
     * each layer in layers array.
     */
    private ArrayList<IImage> layer1, layer2, layer3;

    /**
     * Second and third layer of images.
     */
//    private volatile ArrayList<IImage> layer2, layer3;

    /**
     * Width and height of the canvas.
     */
//    private int screenX, screenY;
//
//    private Point p;

    /**
     * This is the constructor for the thread.
     * @param surfaceHolder handler for the SurfaceView's canvas
     * @param layers reference to the presenter's background image list
     */
//    PanelThread(final SurfaceHolder surfaceHolder, final HashMap<Object, IImage> layer1,
//                final ArrayList<IImage> layer2, final ArrayList<IImage> layer3) { //}, final SurfaceView panel) {
    PanelThread(final SurfaceHolder surfaceHolder, final ArrayList<IImage>[] layers) {
        this.surfaceHolder = surfaceHolder;
//        drawingPanel = panel;

        paint = new Paint();

        //we will never need to update the layers inside this thread since it will be done in the presenter
        this.layers = layers;
//        this.layer1 = layer1;
//        this.layer2 = layer2;
//        this.layer3 = layer3;
        try {
            layer1 = this.layers[0];
            layer2 = this.layers[1];
            layer3 = this.layers[2];
        } catch (Exception e) {
            //TODO: exception if we weren't passed a three element array
        }
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

    @Override
    public void run() {
        Canvas c;
        while (run) {     //When setRunning(false) occurs, run is
            c = null;      //set to false and loop ends, stopping thread

            //checks if the lockCanvas() method will be success,and if not, will check this statement again
            //credit http://javandroidevelop.blogspot.com/2012/09/all-about-surfaceview-android-lessons.html
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }

            try {


                c = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
//                    synchronized (layers) {

                        // Draw the background color
                        //TODO: change this to black
                        c.drawColor(Color.argb(255, 100, 0, 0));
                        // Choose the brush color for drawing
                        paint.setColor(Color.argb(255, 255, 255, 255));




//                    screenX = c.getWidth();
//                    screenY = c.getHeight();

//
//                    RectF rect = new RectF(p.x, p.y, p.x + 25, p.y + 25);
//                    c.drawRect(rect, paint);

//                        if (layer1.size() > 0) {
                            //credit https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
//                            for (HashMap.Entry<Object, IImage> i : layer1.entrySet()) {
                            for (IImage i : layer1) {

                                //draw the bitmaps to the canvas
                                //credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
                                c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
//                                c.drawBitmap(i.getValue().getImage(), i.getValue().getX(), i.getValue().getY(), paint);
//                        c.drawBitmap(i.getImage(), c.getWidth()/ 2- 13, c.getHeight()/ 2 - 13, paint);
                            }
//                        }
                        //call postInvalidate() instead of onDraw() directly, faster supposedly
//                    drawingPanel.postInvalidate();
//                        if (layer2.size() > 0) {
                            for (IImage i : layer2) {
                                c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
                            }
//                        }
//                        if (layer3.size() > 0) {
                            for (IImage i : layer3) {
                                c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
                            }
//                        }
                    }

//                }

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
