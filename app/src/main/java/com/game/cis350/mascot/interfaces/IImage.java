package com.game.cis350.mascot.interfaces;

import android.graphics.Bitmap;

/**
 * @author Reuben 10/8/2017
 */

public interface IImage {
    /**
     * This is the X getter.
     * @return X coordinate of image
     */
    int getX();
    /**
     * This is the Y getter.
     * @return Y coordinate of image
     */
    int getY();

    /**
     * This is the X setter.
     * @param x value to set Y to
     */
    void setX(int x);

    /**
     * This is the Y setter.
     * @param y value to set Y to
     */
    void setY(int y);

    /**
     * This is the image getter.
     * @return image
     */
    Bitmap getImage();

    /**
     * This is the image setter.
     * @param bitmap bitmap to set image to
     */
    void setImage(Bitmap bitmap);

}
