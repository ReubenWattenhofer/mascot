package com.game.cis350.mascot.models;

import android.view.Display;

import com.game.cis350.mascot.interfaces.models.IDrawable;

import org.junit.Test;

import java.util.ArrayList;

import static com.game.cis350.mascot.models.Model.STEPS;
import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Model
 * @author Filipe 25/11/2017.
 */
public class ModelTest {

    /*
     * Tests the method getMainPlayer()
     */
    @Test
    public void getMainPlayer() throws Exception {
        Collidable expected = new Collidable(Animations.PLAYER, 15, 19);
        Model model = new Model();

        assertEquals(expected, model.getMainPlayer());
    }

    /*
     * Tests the method getBusses()
     */
    @Test
    public void getBusses() throws Exception {
        ArrayList<Collidable> expected = new ArrayList<>();
        Collidable bus1 = new Collidable(Animations.BUS, 12, 18);
        bus1.setDirection(Direction.right);
        bus1.setSteps(STEPS);
        expected.add(bus1);

        Collidable bus2 = new Collidable(Animations.BUS_LEFT, 11, 17);
        bus2.setDirection(Direction.left);
        bus2.setSteps(STEPS);
        expected.add(bus2);

        Model model = new Model();

        assertEquals(expected, model.getBusses());

    }

    /*
     * Tests the method getBoats()
     */
    @Test
    public void getBoats() throws Exception {
        ArrayList<Collidable> expected = new ArrayList<>();
        Collidable boat1 = new Collidable(Animations.BOAT, 12, 14);
        boat1.setDirection(Direction.right);
        boat1.setSteps(STEPS);
        expected.add(boat1);

        Collidable boat2 = new Collidable(Animations.BOAT, 19, 13);
        boat2.setDirection(Direction.right);
        boat2.setSteps(STEPS);
        expected.add(boat2);

        Collidable boat3 = new Collidable(Animations.BOAT_LEFT, 19, 12);
        boat3.setDirection(Direction.left);
        boat3.setSteps(STEPS);
        expected.add(boat3);

        Model model = new Model();

        assertEquals(expected, model.getBoats());
    }

    /*
     * Tests the method getBackground()
     */
    @Test
    public void getBackground() throws Exception {
        int width = 30;
        int height = 30;
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

    /*
     * Tests the method getWidth()
     */
    @Test
    public void getWidth() throws Exception {
        int expected = 30;
        Model model = new Model();

        assertEquals(expected, model.getWidth());
    }

    /*
     * Tests the method getHeight()
     */
    @Test
    public void getHeight() throws Exception {
        int expected = 30;
        Model model = new Model();

        assertEquals(expected, model.getHeight());
    }

    /*
    * Tests the method getMainPlayer()
    */
    @Test
    public void getLeftBoundary() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getLeftBoundary());
    }

    /*
     * Tests the method getMainPlayer()
     */
    @Test
    public void getRightBoundary() throws Exception {
        int expected = 20;
        Model model = new Model();

        assertEquals(expected, model.getRightBoundary());
    }

    /*
     * Tests the method getMainPlayer()
     */
    @Test
    public void getTopBoundary() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getTopBoundary());
    }

    /*
     * Tests the method getMainPlayer()
     */
    @Test
    public void getBottomBoundary() throws Exception {
        int expected = 20;
        Model model = new Model();

        assertEquals(expected, model.getBottomBoundary());
    }

}