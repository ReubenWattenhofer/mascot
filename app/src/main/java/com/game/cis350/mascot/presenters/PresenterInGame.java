package com.game.cis350.mascot.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.game.cis350.mascot.interfaces.IImage;
import com.game.cis350.mascot.interfaces.models.IDrawable;
import com.game.cis350.mascot.interfaces.models.IModel;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.models.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the in-game logic.
 * @author Reuben, Ariel 10/9/2017
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
     * For the presenter thread so it can talk to the SurfaceView in the view.
     */
    private SurfaceHolder holder;

    /**
     * This is the model that the presenter talks to.
     */
    private volatile IModel model;

    /**
     * This is the thread that processes the automated game things, like AI and animation.
     */
    private PresenterGameThread gameThread;

    /**
     * This stores the images to be displayed on the view.  The reason this can't be in the model
     * is because the model would then become platform specific, which we want to avoid.  Instead,
     * the model will store the paths to the images using String, which is native to Java.
     * credit https://stackoverflow.com/questions/29061292/c-sharp-mvc-how-to-save-image-to-my-model
     */
    private HashMap<String, Bitmap> images;

    /**
     * Width and height of the tiles.
     */
    private int tileSize;

    /**
     * The background list of images to give to the view.
     */
    private ArrayList<IImage>[] layers;

    /**
     * Holds the previous motion event.
     */
    private MotionEvent previous;

    /**
     * Initializes the view.
     * @param v view to assign to presenter
     * @param holder SurfaceHolder of view's SurfaceView
     */
    public PresenterInGame(final IViewGame v, final SurfaceHolder holder) {
        view = v;
        context = (Context) v; //this is Android specific
        this.holder = holder;

        previous = null;

        //create the model
        model = new Model();
        model.getMainPlayer().setX(view.getScreenWidth() / 2 - 13);
        model.getMainPlayer().setY(view.getScreenHeight() / 2 - 13);
        //TODO: set the model animations here rather than in the model constructor?

        //create the hashmap
        images = PresenterInfo.getImages(); // new HashMap<String, Bitmap>();

        layers = new ArrayList[3];
        layers[0] = new ArrayList<>();
        layers[1] = new ArrayList<>();
        layers[2] = new ArrayList<>();

        //get the tile size
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("grass", "drawable", "com.game.cis350.mascot"));
        tileSize = b.getWidth();

        // Horzontal starting position of first bus
        int startingPosition = 500;

        // How far to place busses apart (horizontally)
        int widthApart = 100;

        // Vertical position of busses
        int row = 0;

        // Set coordinates of busses
        for (int i = 0; i < model.getBusses().size(); i++) {
            model.getBusses().get(i).setX(startingPosition + widthApart * i);
            model.getBusses().get(i).setY(0);
        }

        //create the grass tiles
        IDrawable[][] back = model.getBackground();
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                back[i][j].setX(j * tileSize);
                back[i][j].setY(i * tileSize);
            }
        }
    }


    @Override
    public void pressedRestart() {
        //this will kill the presenter and create a new instance of it
        view.restart();
    }

    @Override
    public void pressedScreen(final MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

          if (previous == null || previous.getAction() == MotionEvent.ACTION_UP) {
            if (Math.abs(((double) x - (double) view.getScreenWidth() / 2)) - Math.abs((double) y - (double) view.getScreenHeight() / 2) > 0) {
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

    @Override
    public void onResume() {
        //start the game thread
        gameThread = new PresenterGameThread(model, images, layers, holder, view.getScreenWidth(), view.getScreenHeight(), tileSize);
        gameThread.setRunning(true);
        gameThread.start();
    }


    @Override
    public void onPause() {
        //end the thread if it's been created
        if (gameThread != null) {
            try {
                gameThread.setRunning(false);                //Tells thread to stop
                gameThread.join();                           //Removes thread from mem.
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public ArrayList<IImage>[] getLayers() {
        return layers;
    }


    /**
     * This method handles the behavior when "up" is pressed.
     */
    private void pressedUp() {
        //TODO: replace with a notification to the thread that the player is moving up
        model.getMainPlayer().setY(model.getMainPlayer().getY() - 50);
        //TODO: once the player is done moving, update should be called -- this method should be private
        gameThread.update();
    }

    /**
     * This method handles the behavior when "down" is pressed.
     */
    private void pressedDown() {
        //TODO: replace with a notification to the thread that the player is moving down
       model.getMainPlayer().setY(model.getMainPlayer().getY() + 50);
       gameThread.update();
    }

    /**
     * This method handles the behavior when "left" is pressed.
     */
    private void pressedLeft() {
        //TODO: replace with a notification to the thread that the player is moving left
        model.getMainPlayer().setX(model.getMainPlayer().getX() - 50);
        gameThread.update();
    }

    /**
     * This method handles the behavior when "right" is pressed.
     */
    private void pressedright() {
        //TODO: replace with a notification to the thread that the player is moving right
        model.getMainPlayer().setX(model.getMainPlayer().getX() + 50);
        gameThread.update();

    }
}
