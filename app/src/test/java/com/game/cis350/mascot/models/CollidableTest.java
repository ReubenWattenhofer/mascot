package com.game.cis350.mascot.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a JUnit test of the class Collidable
 * @author Filipe 01/11/2017.
 */
public class CollidableTest {

    /**
     * Tests the method setSpeed() and getSpeed()
     */
    @Test
    public void setSpeed() throws Exception {
        int expected = 5;
        Collidable collidable = new Collidable(Animations.BUS, 5, 5);
        collidable.setSpeed(5);

        assertEquals(expected, collidable.getSpeed());
    }

    /**
     * Tests the method setSteps() and getSteps()
     */
    @Test
    public void setSteps() throws Exception {
        int expected = 3;
        Collidable collidable = new Collidable(Animations.BUS, 5, 5);
        collidable.setSteps(3);

        assertEquals(expected, collidable.getSteps());
    }

    /**
     * Tests the method setStepCounter() and getStepCounter()
     */
    @Test
    public void setStepCounter() throws Exception {
        int expected = 4;
        Collidable collidable = new Collidable(Animations.BUS, 5, 5);
        collidable.setStepCounter(4);

        assertEquals(expected, collidable.getStepCounter());
    }

    /**
     * Tests the method decrementStepCounter()
     */
    @Test
    public void decrementStepCounter() throws Exception {
        int neg_expected = 0;
        int pos_expected = 4;
        Collidable collidable = new Collidable(Animations.BUS, 5 ,5);

        collidable.setStepCounter(-1);
        collidable.decrementStepCounter();
        assertEquals(neg_expected, collidable.getStepCounter());

        collidable.setStepCounter(5);
        collidable.decrementStepCounter();
        assertEquals(pos_expected, collidable.getStepCounter());

    }

    /**
     * Tests the method setCollideType()
     */
    @Test
    public void setCollideType() throws Exception {
        String[] animation = Animations.BUS;
        CollideTypes collideType = CollideTypes.crushes;
        CollideTypes expected = CollideTypes.crushes;

        Collidable collidable = new Collidable(animation, 5, 5);

        collidable.setCollideType(collideType);

        assertEquals(expected, collideType);
    }

    /**
     * Tests the method setDirection() and getDirection()
     */
    @Test
    public void setDirection() throws Exception {
        Direction expected = Direction.left;
        Collidable collidable = new Collidable(Animations.BUS_LEFT, 5, 5);
        collidable.setDirection(Direction.left);

        assertEquals(expected, collidable.getDirection());
    }

    /**
     * Tests the method collideWith()
     */
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