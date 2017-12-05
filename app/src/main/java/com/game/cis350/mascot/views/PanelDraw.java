package com.game.cis350.mascot.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.game.cis350.mascot.interfaces.IImage;
import java.util.ArrayList;

/**
 * This class updates the SurfaceView in the view.
 * @author Reuben 10/15/2017
 */

public class PanelDraw {
    /**
     * This is the handler for the canvas of the SurfaceView we're working with.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Tint to apply to the sprites.
     * credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
     */
    private Paint paint;

    /**
     * The list of layer1 to draw on the screen.
     */
    private ArrayList<IImage>[] layers;

    /**
     * Each layer in layers array.
     */
    private ArrayList<IImage> layer1, layer2, layer3;

    /**
     * This is the constructor for the thread.
     * @param surfaceHolder handler for the SurfaceView's canvas
     * @param layers reference to the presenter's background image list
     * @throws Exception if incorrect array size is passed
     */

    public PanelDraw(final SurfaceHolder surfaceHolder, final ArrayList<IImage>[] layers) throws Exception {
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();

        //we will never need to update the layers inside this thread since it will be done in the presenter
        this.layers = layers;

        try {
            layer1 = this.layers[0];
            layer2 = this.layers[1];
            layer3 = this.layers[2];
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * Handles drawing to the canvas of the SurfaceView in the view.
     */
    public void draw() {
        Canvas c = null;

            //checks if the lockCanvas() method will be success,and if not, will check this statement again
            //credit http://javandroidevelop.blogspot.com/2012/09/all-about-surfaceview-android-lessons.html
            if (!surfaceHolder.getSurface().isValid()) {
                return;
            }

            try {


                c = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    // Draw the background color
//                    c.drawColor(Color.argb(255, 100, 0, 0));
                    c.drawColor(Color.argb(255, 0, 0, 0));
                    // Choose the brush color for drawing
                    paint.setColor(Color.argb(255, 255, 255, 255));

                    //draw the three layers, one on top of the other
                    for (IImage i : layer1) {
                        //draw the bitmaps to the canvas
                        //credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
                        c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);

                    }
                    for (IImage i : layer2) {
                        c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
                    }

                    for (IImage i : layer3) {
                        c.drawBitmap(i.getImage(), i.getX(), i.getY(), paint);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
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

