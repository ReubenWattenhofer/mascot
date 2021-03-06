package com.game.cis350.mascot;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**

 * This method is used for swipe detection.
 * Code from: https://stackoverflow.com/questions/13095494/how-to-detect-swipe-direction-between-left-right-and-up-down
 * @author Ariel 10/6/2017
 **/

public class OnSwipeListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * Tag.
         */
        private static final String TAG = "OnSwipeListener";

        @Override
        public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {

            // Grab two events located on the plane at e1=(x1, y1) and e2=(x2, y2)
            // Let e1 be the initial event
            // e2 can be located at 4 different positions, consider the following diagram
            // (Assume that lines are separated by 90 degrees.)
            //
            //
            //         \ A  /
            //          \  /
            //       D   e1   B
            //          /  \
            //         / C  \
            //
            // So if (x2,y2) falls in region:
            //  A => it's an UP swipe
            //  B => it's a RIGHT swipe
            //  C => it's a DOWN swipe
            //  D => it's a LEFT swipe
            //

            float x1 = e1.getX();
            float y1 = e1.getY();

            float x2 = e2.getX();
            float y2 = e2.getY();

            SwipeDirection direction = getDirection(x1, y1, x2, y2);
            onSwipe(direction);
            return onSwipe(direction);
        }

        /**
         * Override this method. The Direction enum will tell you how the user swiped.
         *
         * @param direction direction
         * @return true if swipe
         **/
        public boolean onSwipe(final SwipeDirection direction) {
            return false;
        }

        /**
         * Given two points in the plane p1=(x1, x2) and p2=(y1, y1), this method
         * returns the direction that an arrow pointing from p1 to p2 would have.
         *
         * @param x1 the x position of the first point
         * @param y1 the y position of the first point
         * @param x2 the x position of the second point
         * @param y2 the y position of the second point
         * @return the direction
         */
        public SwipeDirection getDirection(final float x1, final float y1, final float x2, final float y2) {
            double angle = getAngle(x1, y1, x2, y2);
            return SwipeDirection.get(angle);
        }

        /**
         * Finds the angle between two points in the plane (x1,y1) and (x2, y2)
         * The angle is measured with 0/360 being the X-axis to the right, angles
         * increase counter clockwise.
         *
         * @param x1 the x position of the first point
         * @param y1 the y position of the first point
         * @param x2 the x position of the second point
         * @param y2 the y position of the second point
         * @return the angle between two points
         */
        public double getAngle(final float x1, final float y1, final float x2, final float y2) {

            double rad = Math.atan2(y1 - y2, x2 - x1) + Math.PI;
            return (rad * 180 / Math.PI + 180) % 360;
        }

    /**
     * Enum for directions.
     */
    public enum SwipeDirection {
        /**
         * Up.
         */
        up,
        /**
         * Down.
         */
        down,
        /**
         * Left.
         */
        left,
        /**
         * Right.
         */
        right;

        /**
         * Returns a direction given an angle.
         * Directions are defined as follows:
         * <p>
         * Up: [45, 135]
         * Right: [0,45] and [315, 360]
         * Down: [225, 315]
         * Left: [135, 225]
         *
         * @param angle an angle from 0 to 360 - e
         * @return the direction of an angle
         */
        public static SwipeDirection get(final double angle) {
            if (inRange(angle, 45, 135)) {
                Log.d(TAG, "UP");
                return SwipeDirection.up;
            } else if (inRange(angle, 0, 45) || inRange(angle, 315, 360)) {
                Log.d(TAG, "RIGHT");
                return SwipeDirection.right;
            } else if (inRange(angle, 225, 315)) {
                Log.d(TAG, "DOWN");
                return SwipeDirection.down;
            } else {
                Log.d(TAG, "LEFT");
                return SwipeDirection.left;
            }

        }

        /**
         * @param angle an angle
         * @param init  the initial bound
         * @param end   the final bound
         * @return returns true if the given angle is in the interval [init, end).
         */
        private static boolean inRange(final double angle, final float init, final float end) {
            return (angle >= init) && (angle < end);
        }

    }

}
