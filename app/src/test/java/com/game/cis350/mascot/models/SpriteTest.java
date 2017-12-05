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
public class SpriteTest {

    @Test
    public void getCurrentFrame() throws Exception {
        Sprite sprite = new Sprite(Animations.BUS_LEFT, 2, 5);
        Sprite test = new Sprite(Animations.BUS_LEFT, 4, 4);
        String expected = sprite.getCurrentFrame();

        assertEquals(expected, test.getCurrentFrame());
    }

    @Test
    public void getFrames() throws Exception {
        String[] expectedClone = Animations.BOAT;
        Sprite test = new Sprite(Animations.BUS, 5, 5);
        test.setFrames(Animations.BOAT);

        assertEquals(expectedClone, test.getFrames());
    }

    @Test
    public void getX() throws Exception {
        int expected = 3;
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 1, 1);
        sprite.setX(3);

        assertEquals(expected, sprite.getX());
    }

    @Test
    public void getY() throws Exception {
        int expected = 4;
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 1, 1);
        sprite.setY(4);

        assertEquals(expected, sprite.getY());
    }

    @Test
    public void setCollideType() throws Exception {
        CollideTypes expected = CollideTypes.crushes;
        Sprite sprite = new Sprite(Animations.BUS_LEFT, 3, 4);

        sprite.setCollideType(CollideTypes.crushes);

        assertEquals(expected, sprite.getCollideType());
    }

    @Test
    public void getCollideType() throws Exception {
        CollideTypes expected = CollideTypes.getsCrushed;
        Sprite sprite = new Sprite(Animations.PLAYER, 10, 11);

        sprite.setCollideType(CollideTypes.getsCrushed);

        assertEquals(expected, sprite.getCollideType());
    }

    /**
     * Tests the method testHashCode()
     */
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

    /**
     * Tests the method incrementFrame()
     */
    @Test
    public void incrementFrame() throws Exception {
        String[] animation = Animations.BUS;
        Sprite sprite = new Sprite(animation, 4, 3);
        sprite.incrementFrame();
        int expected = sprite.maxFrame; // = 0

        assertEquals(expected, sprite.frame);
    }

    /**
     * Tests the method setFrames()
     */
    @Test
    public void setFrames() throws Exception {
        String[] expectedClone = Animations.BUS_LEFT;
        Sprite test = new Sprite(Animations.BUS, 5, 5);
        test.setFrames(Animations.BUS_LEFT);

        assertEquals(expectedClone, test.getFrames());

    }

    /**
     * Tests the method setX()
     */
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

    /**
     * Tests the method setY()
     */
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

    /**
     * Tests the method equals()
     */
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