package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.IDrawable;
import com.game.cis350.mascot.interfaces.models.IModel;

import java.util.ArrayList;

/**
 * This class maintains the state and behavior of the game components.
 * @author Reuben, Ariel 11/19/2017.
 */

public class Model implements IModel {
    /**
     * Step number for mascot.
     */
    public static final int STEPS = 6;

    /**
     * Sample object that model would store.
     */
    private Collidable player;

    /**
     * Bus objects that model would store.
     */
    private ArrayList<Collidable> busses;

    /**
     * Boat objects that model would store.
     */
    private ArrayList<Collidable> boats;

    /**
     * Grid of background sprites.
     */
    private IDrawable[][] background;

    /**
     * Width and height of the map in tiles.
     */
    private int width, height;

    /**
     * Boundary width and height of the map in tiles; gameplay occurs within boundaries.
     */
    private int bWidth, bHeight, left, right, top, bottom;

    /**
     * Constructor for the model.
     */
    //TODO: pass in a filepath to the model so it can create a map from non-hardcoded data (low priority)
    public Model() {
        //set the dimensions of the map
        /*
        Note: the top-left corner of the map is [0][0], the top-right corner is [0][width-1], and
        the bottom-right corner is [height-1][width-1]
         */
        width = 30;
        height = 30;

        bWidth = 10;
        bHeight = 10;

        left = (width - bWidth) / 2;
        right = (width + bWidth) / 2;
        top = (height - bHeight) / 2;
        bottom = (height + bHeight) / 2;

        //start the player in the bottom middle of the playable map
        player = new Collidable(Animations.PLAYER, width / 2,  height - bHeight - 1);
        player.setSteps(STEPS);

        busses = new ArrayList<>();

        // Horizontal starting position of bus in tiles
        int columnBus = left + 2;

        // Vertical position of bus in tiles
        int rowBus = top + 8; //offset by one more than target row since row starts at 0

        Collidable c = new Collidable(Animations.BUS, columnBus, rowBus);
        c.setDirection(Direction.right);
        c.setSteps(STEPS);
        busses.add(c);

        // Horizontal starting position of bus in tiles
        columnBus = left + 1;

        // Vertical position of bus in tiles
        rowBus = top + 7; //offset by one more than target row since row starts at 0

        c = new Collidable(Animations.BUS_LEFT, columnBus, rowBus);
        c.setDirection(Direction.left);
        c.setSteps(STEPS);
        busses.add(c);

        // Add right moving busses
//        for (int i = 0; i < 1; i++) {
//            Collidable c = new Collidable(Animations.BUS, (startingPositionBus) + (widthApartBus * i), rowBus);
//            c.setDirection(Direction.right);
//            c.setSteps(STEPS);
//            busses.add(c);
//        }

        boats = new ArrayList<>();

        // Horizontal starting position of boat in tiles
        int columnBoat = left + 2;

        // Vertical position of boats in tiles
        int rowBoat = top + 4;

        // Add right moving boats
        Collidable currentBoat = new Collidable(Animations.BOAT, columnBoat, rowBoat);
        currentBoat.setDirection(Direction.right);
        currentBoat.setSteps(STEPS);
        boats.add(currentBoat);

        // Horizontal starting position of boat in tiles
        columnBoat = left + 9;

        // Vertical position of boats in tiles
        rowBoat = top + 3;

        // Add right moving boats
        currentBoat = new Collidable(Animations.BOAT, columnBoat, rowBoat);
        currentBoat.setDirection(Direction.right);
        currentBoat.setSteps(STEPS);
        boats.add(currentBoat);

        // Horizontal starting position of boat in tiles
        columnBoat = left + 9;

        // Vertical position of boats in tiles
        rowBoat = top + 2;

        // Add left moving boats
        currentBoat = new Collidable(Animations.BOAT_LEFT, columnBoat, rowBoat);
        currentBoat.setDirection(Direction.left);
        currentBoat.setSteps(STEPS);
        boats.add(currentBoat);

//        for (int i = 0; i < 1; i++) {
//            Collidable currentBoat = new Collidable(Animations.BOAT, (startingPositionBoat) + (widthApartBoat * i), rowBoat);
//            currentBoat.setDirection(Direction.right);
//            currentBoat.setSteps(STEPS);
//            boats.add(currentBoat);
//        }

        //add background tiles for water
        background = new IDrawable[width][height];
        for (int i = 0; i < height / 2; i++) {
            for (int j = 0; j < width; j++) {
                background[i][j] = new Sprite(Animations.WATER, j, i);
                background[i][j].setCollideType(CollideTypes.crushes);
            }
        }

        //add background tiles for grass
        for (int i = height / 2; i < height; i++) {
            for (int j = 0; j < width; j++) {
                background[i][j] = new Sprite(Animations.GRASS, j, i);
            }
        }

    }

    @Override
    public Collidable getMainPlayer() {
        return player;
    }

    @Override
    public ArrayList<Collidable> getBusses() {
        return busses;
    }

    @Override
    public ArrayList<Collidable> getBoats() {
        return boats;
    }

    @Override
    public IDrawable[][] getBackground() {
        return background;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getLeftBoundary() {
        return left;
    }

    @Override
    public int getRightBoundary() {
        return right;
    }

    @Override
    public int getTopBoundary() {
        return top;
    }

    @Override
    public int getBottomBoundary() {
        return bottom;
    }
}
