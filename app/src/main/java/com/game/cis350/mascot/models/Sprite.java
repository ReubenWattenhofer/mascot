package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.IDrawable;

import java.util.Objects;


/**
 * This class is the basic class for objects that are drawn to the screen.
 * @author Reuben
 * 9/20/2017
 */

public class Sprite implements IDrawable {
    /**
     * Stores the list of sprites to loop through.
     */
    private String[] animation;

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
    public Sprite(final String[] animation, final int x, final int y) {
        this.animation = animation;
        this.x = x;
        this.y = y;

        frame = 0;
        maxFrame = animation.length - 1;
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
        return animation[frame];
    }

    @Override
    public String[] getFrames() {
        return animation;
    }

    @Override
    public void setFrames(final String[] frames) {
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

    //Override the equals() and hashCode() so we can properly add Sprite to a collection
    //credit https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Sprite)) {
            return false;
        }

        Sprite sprite = (Sprite) o;
        return Objects.equals(animation, sprite.animation)
                && frame == sprite.frame
                && maxFrame == sprite.maxFrame
                && x == sprite.x
                && y == sprite.y;
    }

    //credit https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
    @Override
    public int hashCode() {
        return Objects.hash(animation, frame, maxFrame, x, y);
    }

}
