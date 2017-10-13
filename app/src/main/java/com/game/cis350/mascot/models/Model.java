package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.IModel;

import java.util.ArrayList;

/**
 * This class maintains the state and behavior of the game components.
 * @author Reuben, Ariel 10/11/2017.
 */

public class Model implements IModel{
    /**
     * Sample object that model would store.
     */
    private Collidable player;

    /**
     * Bus objects that model would store.
     */
    ArrayList<Collidable> busses;


    /**
     * Constructor for the model.
     */
    public Model() {
        ArrayList<String> images = new ArrayList<String>();
        images.add("louie");

        ArrayList<String> busImages = new ArrayList<String>();
        busImages.add("bus");

        player = new Collidable(images, 0, 0);

        busses = new ArrayList<Collidable>();

        // Add busses
        for(Collidable currentBus : busses) {
            busses.add(new Collidable(busImages,0,0));
        }

    }

    @Override
    public Collidable getMainPlayer() {
        return player;
    }

    @Override
    public ArrayList<Collidable> getBusses() { return busses; }
}
