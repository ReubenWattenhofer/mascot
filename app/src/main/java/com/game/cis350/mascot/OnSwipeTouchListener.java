package com.game.cis350.mascot;


import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * This class is used for gesture detection.
 * Copied and pasted from https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
 * @author Reuben 12/1/2017
 */
public class OnSwipeTouchListener implements OnTouchListener {

    /**
     * Does the actual detecting.
     */
    private final GestureDetector gestureDetector;

    /**
     * Constructor.
     * @param ctx context
     */
    public OnSwipeTouchListener(final Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * Inner class where the real work gets done.
     */
    private final class GestureListener extends SimpleOnGestureListener {

        /**
         * Threshold.
         */
        private static final int SWIPE_THRESHOLD = 100;
        /**
         * Velocity threshold.
         */
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(final MotionEvent e) {
            down(e);
            return true;
        }

        @Override
        public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    /**
     * Handles swipe right.
     */
    public void onSwipeRight() {
    }

    /**
     * Handles swipe left.
     */
    public void onSwipeLeft() {
    }

    /**
     * Handles swipe up.
     */
    public void onSwipeTop() {
    }

    /**
     * Handles swipe down.
     */
    public void onSwipeBottom() {
    }

    /**
     * Handles down press.
     * @param e motion event
     */
    public void down(final MotionEvent e) {

    }
}
