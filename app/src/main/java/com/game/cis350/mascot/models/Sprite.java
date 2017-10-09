package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.IDrawable;

import java.util.ArrayList;

/**
 * This class is the basic class for objects that are drawn to the screen.
 * @author Reuben
 * 9/20/2017
 */

class Sprite implements IDrawable {
    /**
     * Stores the list of sprites to loop through.
     */
    private ArrayList<String> animation;

    /**
     * This is the frame of the animation that the sprite is currently on.
     */
    private int frame, maxFrame;

    /**
     * This is the coordinates of the Sprite.
     */
    private int x, y;

    /**
     * Constructor of the class.
     * @param animation list of sprites to use when animating
     * @param x x coordinate
     * @param y y coordinate
     */
    public Sprite(final ArrayList<String> animation, final int x, final int y) {
        this.animation = animation;
        this.x = x;
        this.y = y;

        frame = 0;
        maxFrame = animation.size() - 1;
    }

    @Override
    public void incrementFrame() {
        frame++;
        if (frame > maxFrame) {
            frame = 0;
        }
    }

    @Override
    public String getCurrentFrame() {
        return animation.get(frame);
    }

    @Override
    public ArrayList<String> getFrames() {
        return animation;
    }

    @Override
    public void setFrames(final ArrayList<String> frames) {
        animation = frames;
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
