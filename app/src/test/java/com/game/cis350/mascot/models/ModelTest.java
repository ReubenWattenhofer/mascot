package com.game.cis350.mascot.models;

import android.view.Display;

import com.game.cis350.mascot.interfaces.models.IDrawable;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Model
 * @author Filipe 25/11/2017.
 */
public class ModelTest {

    /**
     * Tests the method getMainPlayer()
     */
    @Test
    public void getMainPlayer() throws Exception {
        Collidable expected = new Collidable(Animations.PLAYER, 5, 9);
        Model model = new Model();

        assertEquals(expected, model.getMainPlayer());
    }

    /**
     * Tests the method getBusses()
     */
    @Test
    public void getBusses() throws Exception {
        ArrayList<Collidable> expected = new ArrayList<Collidable>();
        Collidable bus1 = new Collidable(Animations.BUS, 2, 8);
        Collidable bus2 = new Collidable(Animations.BUS, 1, 7);

        expected.add(bus1);
        expected.add(bus2);

        Model model = new Model();

        assertEquals(expected, model.getBusses());
    }

    /**
     * Tests the method getBoats()
     */
    @Test
    public void getBoats() throws Exception {
        ArrayList<Collidable> expected = new ArrayList<Collidable>();
        Collidable boat1 = new Collidable(Animations.BUS, 2, 4);
        Collidable boat2 = new Collidable(Animations.BUS, 9, 3);

        expected.add(boat1);
        expected.add(boat2);

        Model model = new Model();

        assertEquals(expected, model.getBoats());
    }

    /**
     * Tests the method getBackground()
     */
    @Test
    public void getBackground() throws Exception {
        int width = 10;
        int height = 10;
        IDrawable[][] expected = new IDrawable[width][height];

        Model model = new Model();

        for (int i = 0; i < height / 2; i++) {
            for (int j = 0; j < width; j++) {
                expected[i][j] = new Sprite(Animations.WATER, j, i);
                //background[i][j].setCollideType(CollideTypes.crushes);
            }
        }

        for (int i = height / 2; i < height; i++) {
            for (int j = 0; j < width; j++) {
                expected[i][j] = new Sprite(Animations.GRASS, j, i);
            }
        }

        assertEquals(expected, model.getBackground());
    }

    /**
     * Tests the method getWidth()
     */
    @Test
    public void getWidth() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getWidth());
    }

    /**
     * Tests the method getHeight()
     */
    @Test
    public void getHeight() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getHeight());
    }

}