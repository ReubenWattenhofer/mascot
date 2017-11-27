package com.game.cis350.mascot.presenters;

import android.graphics.Bitmap;
import android.os.Message;
import android.view.SurfaceHolder;
import android.os.Handler;

import com.game.cis350.mascot.interfaces.models.IDrawable;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.models.Collidable;
import com.game.cis350.mascot.interfaces.models.IModel;
import com.game.cis350.mascot.Image;
import com.game.cis350.mascot.interfaces.IImage;
import com.game.cis350.mascot.models.CollideTypes;
import com.game.cis350.mascot.models.Direction;
import com.game.cis350.mascot.views.PanelDraw;
//import com.game.cis350.mascot.views.DrawingPanel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the worker thread for the game presenter.
 * @author Reuben, Ariel 11/19/2017
 */



//Most of this code comes from http://blog.danielnadeau.io/2012/01/android-canvas-beginners-tutorial.html
class PresenterGameThread extends Thread {

    /**
     * This will kill the thread if set to false.
     */
    private boolean run = false;

    /**
     * This stores the images to be displayed on the view.  The reason this can't be in the model
     * is because the model would then become platform specific, which we want to avoid.  Instead,
     * the model will store the paths to the images using String, which is native to Java.
     * credit https://stackoverflow.com/questions/29061292/c-sharp-mvc-how-to-save-image-to-my-model
     */
    private HashMap<String, Bitmap> images;
    /**

     * The list of images to give to the view.
     */
    //read about volatile here http://tutorials.jenkov.com/java-concurrency/volatile.html
//    private volatile HashMap<Object, IImage> layer1;
    private ArrayList<IImage>[] layers;

    /**
     * Each layer in layers array.
     */
    private ArrayList<IImage> layer1, layer2, layer3;

    /**
     * This is the model that the presenter talks to.
     */
    private IModel model;

    /**
     * This is what updates the SurfaceView inside the view.
     */
    private PanelDraw panel;

    /*
     * Tile width and height of screen.
     */
//    private int tileWidth, tileHeight;

    /**
     * Width and height and center of screen in pixels.
     */
    private int width, height, xCenter, yCenter;

    /**
     * Tile tileHeight and tileWidth.
     */
    private int tileSize;

    /**
     * Current drawing bounds of the screen in units of tile size.
     */
    private int top, bottom, left, right;

    /**
     * Handler for allowing thread to communicate with UI
     */
    private Handler mHandler;

    /**
     * Determine if player is on boat
     */
    private boolean onBoat;

    /**
     * This is the constructor for the thread.
     * @param m reference to model presenter talks to
     * @param images reference to hashmap in presenter
     * @param layers reference to images in parent
     * @param holder reference to SurfaceHolder in view
     * @param sW screen width of device
     * @param sH screen height of device
     * @param tileSize tilesize
     **/
      PresenterGameThread(final IModel m, final HashMap<String, Bitmap> images, final ArrayList<IImage>[] layers,
                          final SurfaceHolder holder, final int sW, final int sH, final int tileSize, Handler h) {

          panel = new PanelDraw(holder, layers);

          model = m;

          mHandler = h;

          //calculate tile tileWidth and tileHeight
//          tileWidth = sW / tileSize;
//          tileHeight = sH / tileSize;
          this.tileSize = tileSize;

          width = sW;
          height = sH;

          xCenter = (width - tileSize) / 2;
          yCenter = (height - tileSize) / 2;

        //create the hashmap
          this.images = images;

          this.layers = layers;

          //separate the layers for later
          try {
              layer1 = this.layers[0];
              layer2 = this.layers[1];
              layer3 = this.layers[2];
          } catch (Exception e) {
              //TODO: exception if we weren't passed a three element array
          }

          //initialize top down left right variables
          update();

    }

    /**
     * This begins and ends the main loop of the thread.
     * @param run run or stop thread
     */
    public void setRunning(final boolean run) { //Allow us to stop the thread
        this.run = run;
    }


    /**
     * This method is called when the player position is changed.
     */
    public void update() {
        //update the drawing bounds of the screen
        top = (model.getMainPlayer().getY() - height / 2) / tileSize - 1;
        bottom = (model.getMainPlayer().getY() + height / 2) / tileSize + 1;
        left = (model.getMainPlayer().getX() - width / 2) / tileSize - 1;
        right = (model.getMainPlayer().getX() + width / 2) / tileSize + 1;

        if (top < 0) {
            top = 0;
        }
        if (bottom >= model.getHeight()) {
            bottom = model.getHeight() - 1;
        }
        if (left < 0) {
            left = 0;
        }
        if (right >= model.getWidth()) {
            right = model.getWidth() - 1;
        }
    }

    @Override
    public void run() {

        while (run) {     //When setRunning(false) occurs, run is
                          //set to false and loop ends, stopping thread

            //TODO: PUT AUTOMATED GAME LOGIC HERE
            //update bus/boat movement, animate all sprites in the game (or figure out which sprites
            //are in the player's view and only update those, but that's an optimization for later)

            //move the player if he should be moved
            Collidable player = model.getMainPlayer();

            // Set message type to none
            int messageType = 2;

            onBoat = false;

            if (player.getStepCounter() > 0) {
                //decrement the counter
                player.decrementStepCounter();
                switch (player.getDirection()) {
                    case up:
                        player.setY(player.getY() - player.getSpeed());
                        break;
                    case down:
                        player.setY(player.getY() + player.getSpeed());
                        break;
                    case left:
                        player.setX(player.getX() - player.getSpeed());
                        break;
                    case right:
                        player.setX(player.getX() + player.getSpeed());
                        break;
                    default:
                        break;
                }

                onBoat = true;
                //update the screen view bounds since the player moved
                update();
            }

//            synchronized (layers) {

//            //put background sprites in layer1
                // Get coordinates of player
                int playerX = model.getMainPlayer().getX();
                int playerY = model.getMainPlayer().getY();

                int xOffset = xCenter - playerX;
                int yOffset = yCenter - playerY;

                layer1.clear();
                layer2.clear();
                layer3.clear();


//            }

            //put buses/boats in layer2

            for (Collidable currentBus : model.getBusses()) {

                // Check if player is hit by bus
                if(player.getX() + tileSize*0.7 > currentBus.getX()
                        && player.getX() <= currentBus.getX() + (tileSize*2)
                        && currentBus.getY() == player.getY()){

                    // Send lose message
                    messageType = 0;
                }

                // Reset boat step counter if needed
                if (currentBus.getStepCounter() <= 0) {
                    currentBus.setStepCounter(currentBus.getSteps());
                }

                if (currentBus.getStepCounter() > 0) {
                    //decrement the counter
                    currentBus.decrementStepCounter();
                    switch (currentBus.getDirection()) {
                        case left:

                            // Check if bus is out of bounds
                            if(currentBus.getX() > 0 - (tileSize * 3)) {

                                // Move bus based on speed
                                currentBus.setX(currentBus.getX() - currentBus.getSpeed());
                            }else {

                                // Move bus to left side if it has reached the right bounds
                                currentBus.setX(tileSize * model.getWidth());
                            }

                            break;

                        case right:

                            // Check if bus is out of bounds
                            if(currentBus.getX() < tileSize * model.getWidth()) {

                                // Move bus based on speed
                                currentBus.setX(currentBus.getX() + currentBus.getSpeed());
                            }else {

                                // Move bus to left side if it has reached the right bounds
                                currentBus.setX(0 - (tileSize * 3));
                            }

                            break;
                        default:
                            break;
                    }
                }
                // Create new image for each bus
                Image i = new Image(images.get(currentBus.getCurrentFrame()), xOffset + currentBus.getX(), yOffset + currentBus.getY());

                layer2.add(i);
            }

            for (Collidable currentBoat : model.getBoats()) {

                // Check if player is on boat
                if (player.getX() + tileSize * 0.5 > currentBoat.getX()
                        && player.getX() <= currentBoat.getX() + (tileSize * 2.5)
                        && currentBoat.getY() == player.getY()
                        && player.getStepCounter() <= 0
                        ) {

                    onBoat = true;
                    
                    // Put player on third of boat that he's closest to (boat is three tiles long)
                    player.setX(currentBoat.getX() + (((player.getX() - currentBoat.getX()) / (tileSize - (tileSize / 10))) * tileSize) + currentBoat.getSpeed());

                    //update the screen view bounds since the player moved
                    update();
                }

                // Reset boat step counter if needed
                if (currentBoat.getStepCounter() <= 0) {
                    currentBoat.setStepCounter(currentBoat.getSteps());
                }

                if (currentBoat.getStepCounter() > 0) {
                    //decrement the counter
                    currentBoat.decrementStepCounter();

                    switch (currentBoat.getDirection()) {
                        case left:

                            // Check if boat is out of bounds
                            if(currentBoat.getX() > 0 - (tileSize * 3)) {

                                currentBoat.setX(currentBoat.getX() - currentBoat.getSpeed());
                            }else {
                                // Move boat to left side if it has reached the right bounds
                                currentBoat.setX(tileSize * model.getWidth());
                            }

                            break;

                        case right:

                            // Check if boat is out of bounds
                            if(currentBoat.getX() < tileSize * model.getWidth()) {

                                currentBoat.setX(currentBoat.getX() + currentBoat.getSpeed());
                            }else {
                                // Move boat to left side if it has reached the right bounds
                                currentBoat.setX(0 - (tileSize * 3));
                            }

                            break;
                        default:
                            break;
                    }
                }

                // Create new image for each boat
                Image i = new Image(images.get(currentBoat.getCurrentFrame()), xOffset + currentBoat.getX(), yOffset + currentBoat.getY());
                layer2.add(i);
            }

            Image t = null;
            IDrawable[][] back = model.getBackground();

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
//                        t = new Image(images.get(back[i][j].getCurrentFrame()), xOffset + back[i][j].getX(), yOffset + back[i][j].getY());

                    //since they're in a grid, they should all be spaced evenly, so we know exactly where they are; this is for performance
//                        t = new Image(images.get(back[i][j].getCurrentFrame()), xOffset + (j * tileSize), yOffset - (i * tileSize));
                    t = new Image(images.get(back[i][j].getCurrentFrame()), xOffset + j * tileSize, yOffset + i * tileSize); // original
                    layer1.add(t);


                    // Check if player is on background tile
                    if(i*tileSize == player.getY() && j*tileSize <= player.getX() && player.getX() <= j*tileSize + tileSize){

                        // Check if player drowns
//                        if(back[i][j].getCollideType() == CollideTypes.crushes && !onBoat){
//
//                            // Send lose message
//                            messageType = 0;
//                        }

                        // Check if player wins
//                        if(back[i][j].getCollideType() == CollideTypes.win){
//
//                            // Send win message
//                            messageType = 1;
//                        }
                    }
                }
            }

            // Check if player is out of bounds
            if(player.getX() < 0 || player.getX() >= tileSize * (model.getWidth()) )
                // Send lose message
                messageType = 0;

            //put the player in layer3
            Image j = new Image(images.get(model.getMainPlayer().getCurrentFrame()), xCenter, yCenter);
            layer3.add(j);

//            }

            // Send message if win or lose
            if(messageType == 1 || messageType == 0) {
                Message completeMessage = mHandler.obtainMessage(messageType);
                completeMessage.sendToTarget();
            }

            //draw to the screen now
            panel.draw();

        }
    }
}

