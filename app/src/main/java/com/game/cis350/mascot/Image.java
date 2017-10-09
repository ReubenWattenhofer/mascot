package com.game.cis350.mascot;

import android.graphics.Bitmap;

import com.game.cis350.mascot.interfaces.IImage;

/**
 * This class stores the properties of a sprite, and is meant to be used to pass the basic
 * draw information to the view.
 * @author Reuben 10/8/2017
 */

public class Image implements IImage {

    /**
     * This is the bitmap to be drawn.
     */
    private Bitmap bitmap;

    /**
     * These are the coordinates to draw the bitmap at.
     */
    private int x, y;

    /**
     * This constructor sets up the image.
     * @param b bitmap parameter
     * @param x x coordinate
     * @param y y coordinate
     */
    public Image(final Bitmap b, final int x, final int y) {
        bitmap = b;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(final int x) {
        this.x = x;
    }

    @Override
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public Bitmap getImage() {
        return bitmap;
    }

    @Override
    public void setImage(final Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
