package com.game.cis350.mascot.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.game.cis350.mascot.interfaces.IImage;
import com.game.cis350.mascot.interfaces.models.ICollidable;
import com.game.cis350.mascot.interfaces.models.IDrawable;
import com.game.cis350.mascot.interfaces.models.IModel;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.models.Collidable;
import com.game.cis350.mascot.models.Direction;
import com.game.cis350.mascot.models.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the in-game logic.
 * @author Reuben, Ariel 11/19/2017
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

        //get the tile size
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("grass", "drawable", "com.game.cis350.mascot"));
//        double difference = b.getWidth() - (((int) b.getWidth() / 6) * 6);
//        if (difference != 0) {}

        int steps = PresenterInfo.STEPS;
        //scale the bitmaps so that they're a factor of the mascot's step number
        b = Bitmap.createScaledBitmap(b, (((int) b.getWidth() / steps) * steps), (((int) b.getHeight() / steps) * steps), false);
        tileSize = b.getWidth();


        //create the model
        model = new Model();

        //set mascot's starting location
        Collidable player = model.getMainPlayer();
        player.setX(player.getX() * tileSize);
        player.setY(player.getY() * tileSize);
//        model.getMainPlayer().setY((model.getHeight() - 1) * tileSize); //view.getScreenHeight() / 2 - 13);
        //TODO: set the model animations here rather than in the model constructor?

        //create the hashmap
        images = PresenterInfo.getImages(); // new HashMap<String, Bitmap>();

        layers = new ArrayList[3];
        layers[0] = new ArrayList<>();
        layers[1] = new ArrayList<>();
        layers[2] = new ArrayList<>();




        /*
          set the mascot's step number to some number
          (does NOT have to be a factor of the tile width; instead, PresenterInfo scales every
          sprite's width to be a factor of step number)
         */
        model.getMainPlayer().setSteps(steps);
        //set the mascot's speed based on the tile width (tiles should be square)
        model.getMainPlayer().setSpeed(tileSize / steps);

        //TODO: set bus and boat speed based on tile width

//        // Horzontal starting position of first bus in tiles
//        int startingPositionBus = 1;
//
//        // How far to place busses apart in tiles (horizontally)
//        int widthApartBus = 1;
//
//        // Vertical position of busses in tiles
//        int rowBus = model.getHeight() - 2; //offset by one more than target row since row starts at 0
//
//        // Set coordinates of busses
        ArrayList<Collidable> busses = model.getBusses();
        for (int i = 0; i < busses.size(); i++) {
            Collidable bus = busses.get(i);
            bus.setX(bus.getX() * tileSize);
            bus.setY(bus.getY() * tileSize);
        }
//        for (int i = 0; i < model.getBusses().size(); i++) {
//            model.getBusses().get(i).setX((startingPositionBus*tileSize) + (widthApartBus * i * tileSize));
//            model.getBusses().get(i).setY(rowBus*tileSize);
//        }



//        // Horzontal starting position of first boat in tiles
//        int startingPositionBoat = 2;
//
//        // How far to place boats apart in tiles (horizontally)
//        int widthApartBoat = 1;
//
//        // Vertical position of boats in tiles
//        int rowBoat = model.getHeight() - 8;
//
//        // Set coordinates of boats
        ArrayList<Collidable> boats = model.getBoats();
        for (int i = 0; i < boats.size(); i++) {
            Collidable boat = boats.get(i);
            boat.setX(boat.getX() * tileSize);
            boat.setY(boat.getY() * tileSize);
        }
//        for (int i = 0; i < model.getBoats().size(); i++) {
//            model.getBoats().get(i).setX((startingPositionBoat*tileSize) + (widthApartBoat * i * tileSize));
//            model.getBoats().get(i).setY(rowBoat*tileSize);
//        }


        //create the background tiles
        IDrawable[][] back = model.getBackground();
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                back[i][j].setX(j * tileSize);
                back[i][j].setY(-i * tileSize);
            }
        }

    }


    @Override
    public void pressedRestart() {
        //dismiss any open windows
        view.dismissWindows();
        //this will kill the presenter and create a new instance of it
        view.restart();
    }

    @Override
    public void pressedQuit() {
        //kill the thread
        onPause();
        //dismiss any open windows
        view.dismissWindows();
        //exit the game and return to the main menu
        view.quit();
    }

    @Override
    public void pressedScreen(final MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (previous == null || previous.getAction() != MotionEvent.ACTION_UP) {
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
//            }
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
        Collidable player = model.getMainPlayer();
        if (player.getStepCounter() <= 0) {
            player.setDirection(Direction.up);
            player.setStepCounter(player.getSteps());
        }
//        model.getMainPlayer().setY(model.getMainPlayer().getY() - 50);
//        gameThread.update();
    }

    /**
     * This method handles the behavior when "down" is pressed.
     */
    private void pressedDown() {
        Collidable player = model.getMainPlayer();
        if (player.getStepCounter() <= 0) {
            player.setDirection(Direction.down);
            player.setStepCounter(player.getSteps());
        }

//        view.showWin();
//       model.getMainPlayer().setY(model.getMainPlayer().getY() + 50);
//       gameThread.update();
    }

    /**
     * This method handles the behavior when "left" is pressed.
     */
    private void pressedLeft() {
        Collidable player = model.getMainPlayer();
        if (player.getStepCounter() <= 0) {
            player.setDirection(Direction.left);
            player.setStepCounter(player.getSteps());
        }
//        view.showLose();
//        model.getMainPlayer().setX(model.getMainPlayer().getX() - 50);
//        gameThread.update();
    }

    /**
     * This method handles the behavior when "right" is pressed.
     */
    private void pressedright() {
        Collidable player = model.getMainPlayer();
        if (player.getStepCounter() <= 0) {
            player.setDirection(Direction.right);
            player.setStepCounter(player.getSteps());
        }
//        model.getMainPlayer().setX(model.getMainPlayer().getX() + 50);
//        gameThread.update();

    }
}
