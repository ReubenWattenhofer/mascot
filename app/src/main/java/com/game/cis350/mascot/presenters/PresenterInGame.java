package com.game.cis350.mascot.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.MotionEvent;

import com.game.cis350.mascot.BuildConfig;
import com.game.cis350.mascot.Image;
import com.game.cis350.mascot.R;
import com.game.cis350.mascot.interfaces.IImage;
import com.game.cis350.mascot.interfaces.models.IModel;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.models.Model;
import com.game.cis350.mascot.views.GameActivity;
import com.game.cis350.mascot.views.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the in-game logic.
 * @author Reuben 9/25/2017
 */

public class PresenterInGame implements IPresenterInGame {

    /**
     * Uses the view to interact with it.
     */
    private IViewGame view;

    /**
     * For context.
     */
    private Context context;

    /**
     * This is the model that the presenter talks to.
     */
    private IModel model;

    /**
     * This stores the images to be displayed on the view.  The reason this can't be in the model
     * is because the model would then become language specific, which we want to avoid.  Instead,
     * the model will store the paths to the images using String, which is native to Java.
     * credit https://stackoverflow.com/questions/29061292/c-sharp-mvc-how-to-save-image-to-my-model
     */
    private HashMap<String, Bitmap> images;

    /**
     * Holds the previous motion event.
     */
    private MotionEvent previous;

    /**
     * Initializes the view.
     * @param v view to assign to presenter
     */
    public PresenterInGame(final IViewGame v) {
        view = v;
        context = (Context) v; //this is Android specific
        previous = null;

        //create the model
        model = new Model();
        model.getMainPlayer().setX(view.getScreenWidth()/ 2 - 13);
        model.getMainPlayer().setY(view.getScreenHeight()/ 2 - 13);

        //create the hashmap
        images = new HashMap<String, Bitmap>();

        //get the file paths for the main player
        ArrayList<String> filePaths = model.getMainPlayer().getFrames();

        //create the bitmaps and store them in the hashmap
        //credit https://stackoverflow.com/questions/25034782/dynamically-choosing-drawable
        for (String s: filePaths) {
            try {
                Bitmap b = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(s, "drawable", "com.game.cis350.mascot"));
                images.put(s, b);
            } catch (Exception e) {
                //TODO: do something here
            }

        }

    }

    @Override
    public void pressedRestart() {

    }

    @Override
    public void pressedScreen(final MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);


          if (previous == null || previous.getAction() == MotionEvent.ACTION_UP) {
            if (Math.abs((double) (x - view.getScreenWidth() / 2)) - Math.abs((double) y - view.getScreenHeight() / 2) > 0) {
                if (x <= view.getScreenWidth() / 2) {
                    pressedLeft();
                } else if (x > view.getScreenWidth() / 2) {
                    pressedright();
                }
            } else {
                if (y <= view.getScreenHeight() / 2) {
                    pressedUp();
                } else if (y > view.getScreenHeight() / 2) {
                    pressedDown();
                }
            }
        }

        previous = event;

    }

    //TODO: create thread to update model behavior and pass image data to view
    //we need to do this because the activity is event driven -- how are we going to update the model
    //while we are waiting for events if we don't have a separate thread?  In other words, the "main"
    //loop is somewhere within native Android code, where we can't reach it.  So we have to create a
    //thread within this presenter as soon as it is created, so it can control the automatic stuff
    //like sprite animation and AI movement.


    /**
     * This method handles the behavior when "up" is pressed.
     */
    private void pressedUp() {
//        Image i = new Image(images.get(model.getMainPlayer().getCurrentFrame()), view.getScreenWidth()/ 2 - 13, view.getScreenHeight()/ 2 - 13);
        model.getMainPlayer().setY(model.getMainPlayer().getY() - 50);

        Image i = new Image(images.get(model.getMainPlayer().getCurrentFrame()), model.getMainPlayer().getX(), model.getMainPlayer().getY());

        ArrayList<IImage> images = new ArrayList<IImage>();
        images.add(i);
        view.update(images);

    }

    /**
     * This method handles the behavior when "down" is pressed.
     */
    private void pressedDown() {
//        model.getMainPlayer().setX(model.getMainPlayer().getX() + 10);
        model.getMainPlayer().setY(model.getMainPlayer().getY() + 50);

        Image i = new Image(images.get(model.getMainPlayer().getCurrentFrame()), model.getMainPlayer().getX(), model.getMainPlayer().getY());

        ArrayList<IImage> images = new ArrayList<IImage>();
        images.add(i);
        view.update(images);

    }

    /**
     * This method handles the behavior when "left" is pressed.
     */
    private void pressedLeft() {
        model.getMainPlayer().setX(model.getMainPlayer().getX() - 50);

        Image i = new Image(images.get(model.getMainPlayer().getCurrentFrame()), model.getMainPlayer().getX(), model.getMainPlayer().getY());

        ArrayList<IImage> images = new ArrayList<IImage>();
        images.add(i);
        view.update(images);

    }

    /**
     * This method handles the behavior when "right" is pressed.
     */
    private void pressedright() {
        model.getMainPlayer().setX(model.getMainPlayer().getX() + 50);

        Image i = new Image(images.get(model.getMainPlayer().getCurrentFrame()), model.getMainPlayer().getX(), model.getMainPlayer().getY());

        ArrayList<IImage> images = new ArrayList<IImage>();
        images.add(i);
        view.update(images);

    }
}
