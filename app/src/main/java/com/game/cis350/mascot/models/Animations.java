package com.game.cis350.mascot.models;


/**
 * This class has the animations for the sprites, so they can reference it instead of holding
 * separate versions of the same thing.
 * @author Reuben 10/15/2017
 */

public final class Animations {
    /**
     *  Animations for the player.
     */
    public static final String[] PLAYER = new String[] {"louie"};

    /**
     *  Animations for the buses.
     */
    public static final String[] BUS = new String[] {"bus"};

    /**
     *  Animations for the grass tiles.
     */
    public static final String[] GRASS = new String[] {"grass"};

    /**
     * This prevents the constructor from being accessed.
     */
    private Animations() { }

}
