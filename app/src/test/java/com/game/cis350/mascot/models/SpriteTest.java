package com.game.cis350.mascot.models;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Sprite
 * @author Filipe 01/11/2017.
 */

// TODO: Add documentation before methods
public class SpriteTest {
    @Test
    public void testHashCode() throws Exception {
        Sprite sprite = new Sprite(Animations.BUS, 0, 0);
        Sprite sprite2 = new Sprite(Animations.BUS, 1, 0);
        Map<Sprite, Integer> map = new HashMap<>();
        map.put(sprite, 1);
        map.put(sprite2, 2);
        assertEquals((int) map.get(sprite), 1);
        assertEquals((int) map.get(sprite2), 2);

    }

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

        assertEquals(expected, sprite.getX(), delta);

    }

    @Test
    public void setY() throws Exception {
        int y = 6;
        int expected = 6;
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 1, 1);
        sprite.setY(y);
        double delta = 0.1;

        assertEquals(expected, sprite.getY(), delta);

    }

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