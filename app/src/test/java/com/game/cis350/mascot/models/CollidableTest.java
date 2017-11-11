package com.game.cis350.mascot.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Collidable
 * @author Filipe 01/11/2017.
 */
public class CollidableTest {

    @Test
    public void setCollideType() throws Exception {
        String[] animation = Animations.BUS;
        CollideTypes collideType = CollideTypes.crushes;
        CollideTypes expected = CollideTypes.crushes;

        Collidable collidable = new Collidable(animation, 5, 5);

        collidable.setCollideType(collideType);

        assertEquals(expected, collideType);
    }

    @Test
    public void setDirection() throws Exception {

    }

    @Test //(expected = Exception.class)
    public void collideWith() throws Exception {
        boolean expected = false;
        Collidable c = new Collidable(Animations.BUS, 1, 1);
        Collidable f = new Collidable(Animations.PLAYER, 2, 2);

        f.setX(1);
        f.setY(1);
        boolean crashed = f.collideWith(c);

        assertEquals(expected, crashed);
    }

}