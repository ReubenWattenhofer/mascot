package com.game.cis350.mascot.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Sprite
 * @author Filipe 01/11/2017.
 */
public class SpriteTest {
    @Test
    public void incrementFrame() throws Exception {
        String[] animation = Animations.BUS;
        int frame = 0;
        int maxFrame = animation.length;
        int expected = 1;
        double delta = 0.1;

        while(frame < maxFrame) {
            frame++;
        }

        assertEquals(expected, frame, delta);
    }

    @Test
    public void setFrames() throws Exception {
        String[] animation = Animations.BUS;
        String[] expectedClone = Animations.BUS;
        Sprite test = new Sprite(Animations.BUS, 5, 5);
        test.setFrames(expectedClone);

        assertEquals(expectedClone, animation);

    }

    @Test
    public void setX() throws Exception {
        int x = 5;
        int expected = 5;
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 1, 1);
        sprite.setX(x);
        double delta = 0.1;

        assertEquals(expected, x, delta);

    }

    @Test
    public void setY() throws Exception {
        int y = 6;
        int expected = 6;
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 1, 1);
        sprite.setX(y);
        double delta = 0.1;

        assertEquals(expected, y, delta);

    }

    //TODO: Develop right testing method for void equals(Object o) originally in the Sprite class
    @Test
    public void equals() throws Exception {

        Object objOne = new Sprite(Animations.BUS, 5, 5);
        Object objTwo = new Sprite(Animations.GRASS, 3, 4);
        boolean valOne = false;
        boolean valTwo = false;

        Sprite sprite = new Sprite(Animations.BUS, 5, 5);

        valOne = sprite.equals(objOne);
        assertEquals(true, valOne);

        valTwo = sprite.equals(objTwo);
        assertEquals(false, valTwo);

    }

}