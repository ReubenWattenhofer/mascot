package com.game.cis350.mascot.models;


/**
 * This class has the animations for the sprites, so they can reference it instead of holding
 * separate versions of the same thing.
 * @author Reuben, Ariel 11/19/2017
 */

public final class Animations {
    /**
     *  Animations for the player.
     */
    public static final String[] PLAYER = new String[] {"louie"};

    /**
     *  Animations for right moving buses.
     */
    public static final String[] BUS = new String[] {"bus"};

    /**
     *  Animations for left moving buses.
     */
    public static final String[] BUS_LEFT = new String[] {"bus_left"};

    /**
     *  Animations for right moving boats.
     */
    public static final String[] BOAT = new String[] {"boat"};

    /**
     *  Animations for left moving boats.
     */
    public static final String[] BOAT_LEFT = new String[] {"boat_left"};

    /**
     *  Animations for the grass tiles.
     */
    public static final String[] GRASS = new String[] {"grass"};

    /**
     *  Animations for the bush tiles.
     */
    public static final String[] BUSH = new String[] {"bush"};

    /**
     *  Animations for the water tiles.
     */
    public static final String[] WATER = new String[] {"water"};

    /**
     * This prevents the constructor from being accessed.
     */
    private Animations() { }

}
