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
        Collidable expected = new Collidable(Animations.PLAYER, 15, 24);
        expected.setSteps(STEPS);
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
        Collidable bus1 = new Collidable(Animations.BUS, 12, 19);
        bus1.setDirection(Direction.right);
        bus1.setSteps(STEPS);
        expected.add(bus1);

        Collidable bus2 = new Collidable(Animations.BUS, 19, 22);
        bus2.setDirection(Direction.right);
        bus2.setSteps(STEPS);
        expected.add(bus2);

        Collidable bus3 = new Collidable(Animations.BUS_LEFT, 11, 21);
        bus3.setDirection(Direction.left);
        bus3.setSteps(STEPS);
        expected.add(bus3);

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
        Collidable boat1 = new Collidable(Animations.BOAT, 11, 11);
        boat1.setDirection(Direction.right);
        boat1.setSteps(STEPS);
        expected.add(boat1);

        Collidable boat2 = new Collidable(Animations.BOAT, 16, 11);
        boat2.setDirection(Direction.right);
        boat2.setSteps(STEPS);
        expected.add(boat2);

        Collidable boat3 = new Collidable(Animations.BOAT_LEFT, 19, 12);
        boat3.setDirection(Direction.left);
        boat3.setSteps(STEPS);
        expected.add(boat3);

        Collidable boat4 = new Collidable(Animations.BOAT_LEFT, 10, 12);
        boat4.setDirection(Direction.left);
        boat4.setSteps(STEPS);
        expected.add(boat4);

        Collidable boat5 = new Collidable(Animations.BOAT_LEFT, 12, 13);
        boat5.setDirection(Direction.left);
        boat5.setSteps(STEPS);
        expected.add(boat5);

        Collidable boat6 = new Collidable(Animations.BOAT_LEFT, 19, 13);
        boat6.setDirection(Direction.left);
        boat6.setSteps(STEPS);
        expected.add(boat6);

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
        int bWidth = 10;
        int bHeight = 20;
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


        // Fill background with bushes for border
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                expected[i][j] = new Sprite(Animations.BUSH, j, i);
            }
        }

        // Add in parts for grass
        for (int i = (height - bHeight) / 2; i < ((height - bHeight) / 2) + bHeight; i++) {
            for (int j = (width - bWidth) / 2; j < ((width - bWidth) / 2) + bWidth; j++) {
                expected[i][j].setFrames(Animations.GRASS);
            }
        }

        for (int i = (height - bHeight) / 2; i < ((height - bHeight) / 2) + bHeight; i++) {

            // Add parts for road top
            if (i == 18 || i == 21) {
                for (int j = 0; j < width; j++) {
                    expected[i][j].setFrames(Animations.ROAD_TOP);
                }
            }

            // Add parts for road bottom
            if (i == 19 || i == 22) {
                for (int j = 0; j < width; j++) {
                    expected[i][j].setFrames(Animations.ROAD_BOTTOM);
                }
            }

            // Add parts for water
            if (i == 11 || i == 12 || i == 13) {
                for (int j = 0; j < width; j++) {
                    expected[i][j].setFrames(Animations.WATER);
                    expected[i][j].setCollideType(CollideTypes.crushes);
                }
            }

            // Add parts for win
            if (i == 5) {
                for (int j = (width - bWidth) / 2; j < ((width - bWidth) / 2) + bWidth; j++) {
                    expected[i][j].setFrames(Animations.WIN);
                    expected[i][j].setCollideType(CollideTypes.win);
                }
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
        int expected = 5;
        Model model = new Model();

        assertEquals(expected, model.getTopBoundary());
    }

    /*
     * The function getBottomBoundary() from the Model class returns the bottom boundary of the map.
     * This method evaluates getBottomBoundary()
     */
    @Test
    public void getBottomBoundary() throws Exception {
        int expected = 25;
        Model model = new Model();

        assertEquals(expected, model.getBottomBoundary());
    }

}