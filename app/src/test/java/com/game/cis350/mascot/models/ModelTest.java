package com.game.cis350.mascot.models;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Model
 * @author Filipe 25/11/2017.
 */
public class ModelTest {
    @Test
    public void getMainPlayer() throws Exception {
        Collidable expected = new Collidable(Animations.PLAYER, 5, 9);
        Model model = new Model();

        assertEquals(expected, model.getMainPlayer());
    }

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

    @Test
    public void getBackground() throws Exception {
    }

    @Test
    public void getWidth() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getWidth());
    }

    @Test
    public void getHeight() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getHeight());
    }

}