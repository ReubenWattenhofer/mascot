package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.IDrawable;
import com.game.cis350.mascot.interfaces.models.IModel;

import java.util.ArrayList;

/**
 * This class maintains the state and behavior of the game components.
 * @author Reuben, Ariel 10/11/2017.
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
        player = new Collidable(Animations.PLAYER, 0, 0);

        busses = new ArrayList<>();

        // Add busses
        busses.add(new Collidable(Animations.BUS, 0, 0));

        //set the dimensions of the map
        width = 10;
        height = 10;

        //add grass tiles
        background = new IDrawable[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                background[i][j] = new Sprite(Animations.GRASS, 0, 0);
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
