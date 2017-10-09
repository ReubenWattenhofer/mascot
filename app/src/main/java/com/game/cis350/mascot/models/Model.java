package com.game.cis350.mascot.models;

import com.game.cis350.mascot.interfaces.models.IModel;

import java.util.ArrayList;

/**
 * This class maintains the state and behavior of the game components.
 * @author Reuben 9/24/2017.
 */

public class Model implements IModel{
    /**
     * Sample object that model would store.
     */
    private Collidable player;

    /**
     * Constructor for the model.
     */
    public Model() {
        ArrayList<String> images = new ArrayList<String>();
        images.add("louie");
        player = new Collidable(images, 0, 0);
    }

    @Override
    public Collidable getMainPlayer() {
        return player;
    }
}
