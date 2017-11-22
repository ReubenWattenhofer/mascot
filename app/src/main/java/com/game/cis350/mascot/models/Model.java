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
     * Constructor for the model.
     */
    //TODO: pass in a filepath to the model so it can create a map from non-hardcoded data (low priority)
    public Model() {
        //set the dimensions of the map
        /*
        Note: the top-left corner of the map is [0][0], the top-right corner is [0][width-1], and
        the bottom-right corner is [height-1][width-1]
         */
        width = 10;
        height = 10;

        player = new Collidable(Animations.PLAYER, width / 2, height - 1);

        busses = new ArrayList<>();

        // Horizontal starting position of first bus in tiles
        int startingPositionBus = 1;

        // How far to place busses apart in tiles (horizontally)
        int widthApartBus = 1;

        // Vertical position of busses in tiles
        int rowBus = height - 2; //offset by one more than target row since row starts at 0

        // Add busses
        for (int i = 0; i < 1; i++) {
            busses.add(new Collidable(Animations.BUS, (startingPositionBus) + (widthApartBus * i), rowBus));
        }

        boats = new ArrayList<>();

        // Horzontal starting position of first boat in tiles
        int startingPositionBoat = 2;

        // How far to place boats apart in tiles (horizontally)
        int widthApartBoat = 1;

        // Vertical position of boats in tiles
        int rowBoat = height - 8;

        // Add boats
        for (int i = 0; i < 1; i++) {
            boats.add(new Collidable(Animations.BOAT, (startingPositionBoat) + (widthApartBoat * i), rowBoat));
        }

        //add background tiles for water
        background = new IDrawable[width][height];
        for (int i = 0; i < height / 2; i++) {
            for (int j = 0; j < width; j++) {
                background[i][j] = new Sprite(Animations.WATER, j, i);
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
}
