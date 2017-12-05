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
     * The function getMainPlayer() from the Model class returns a Collidable object
     * that has the attributes of a characteristic player.
     * this method evaluates the getMainPlayer()
     */
    @Test
    public void getMainPlayer() throws Exception {
        Collidable expected = new Collidable(Animations.PLAYER, 15, 19);
        Model model = new Model();

        assertEquals(expected, model.getMainPlayer());
    }

    /*
     * The function getBusses() from the Model class returns an Arraylist of Collidable objects.
     * The said Arraylist is comprised of busses that have a set of distinct and particular
     * characteristics.
     * This method evaluates getBusses()
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
     * The function getBoats() from the Model class returns an Arraylist of Collidable objects.
     * The said Arraylist is comprised of boats that have a set of distinct and particular
     * characteristics.
     * This method evaluates getBusses()
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
     * The function getBackground() from the Model class returns a 2-Dimensions Array of datatype
     * IDrawable.
     * The said 2-D Array is comprised of background spaces that will be filled with either grass
     * or water.
     * This method evaluates getBackground()
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
     * The function getWidth() from the Model class returns the width of the map.
     * This method evaluates getWidth()
     */
    @Test
    public void getWidth() throws Exception {
        int expected = 30;
        Model model = new Model();

        assertEquals(expected, model.getWidth());
    }

    /*
     * The function getHeight() from the Model class returns the height of the map.
     * This method evaluates getHeight()
     */
    @Test
    public void getHeight() throws Exception {
        int expected = 30;
        Model model = new Model();

        assertEquals(expected, model.getHeight());
    }

    /*
     * The function getLeftBoundary() from the Model class returns the left boundary of the map.
     * This method evaluates getLeftBoundary()
     */
    @Test
    public void getLeftBoundary() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getLeftBoundary());
    }

    /*
     * The function getRightBoundary() from the Model class returns the right boundary of the map.
     * This method evaluates getRightBoundary()
     */
    @Test
    public void getRightBoundary() throws Exception {
        int expected = 20;
        Model model = new Model();

        assertEquals(expected, model.getRightBoundary());
    }

    /*
     * The function getTopBoundary() from the Model class returns the top boundary of the map.
     * This method evaluates getTopBoundary()
     */
    @Test
    public void getTopBoundary() throws Exception {
        int expected = 10;
        Model model = new Model();

        assertEquals(expected, model.getTopBoundary());
    }

    /*
     * The function getBottomBoundary() from the Model class returns the bottom boundary of the map.
     * This method evaluates getBottomBoundary()
     */
    @Test
    public void getBottomBoundary() throws Exception {
        int expected = 20;
        Model model = new Model();

        assertEquals(expected, model.getBottomBoundary());
    }

}