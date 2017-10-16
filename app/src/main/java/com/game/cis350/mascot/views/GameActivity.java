package com.game.cis350.mascot.views;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.game.cis350.mascot.R;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.presenters.PresenterInGame;
import com.game.cis350.mascot.presenters.PresenterInfo;


/**
 * This class is the View (MVP View, not Android View) for the game.
 * @author Reuben 10/12/2017
 */

public class GameActivity extends AppCompatActivity implements IViewGame {

    /**
     * Handles the logic when stuff gets pressed/interacted with.
     */
    private IPresenterInGame presenter;

    /**
     * This is where the sprites get drawn.
     */
//    private DrawingPanel gamePanel;
    private SurfaceView gamePanel;

    /**
     * This is the holder for the SurfaceView in the layout.
     */
    private SurfaceHolder holder;

    /**
     * Stores the width and height of the screen.
     */
    private int screenX, screenY;

    /**
     * This is the worker thread where the drawing is done.
     */
    private PanelThread thread;

    /**
     * used to initialize the presenter.
     */
    private GameActivity thisActivity;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        thisActivity = this;

        //lock the screen to portrait mode
        //credit https://stackoverflow.com/questions/2366706/how-to-lock-orientation-during-runtime
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);

        //set the toolbar as the activity's app bar
        //credit to https://developer.android.com/training/appbar/setting-up.html#add-toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mascot");


        //credit http://gamecodeschool.com/android/coding-a-space-invaders-game/
        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);


        gamePanel = (SurfaceView) findViewById(R.id.surfaceView);
        holder = gamePanel.getHolder();

        //initialize PresenterInfo
        PresenterInfo.create(this);

        //credit https://stackoverflow.com/questions/4142090/how-to-retrieve-the-dimensions-of-a-view
        ViewTreeObserver vto = gamePanel.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                screenX = gamePanel.getWidth();
                screenY = gamePanel.getHeight();

                //add touch listener to game panel
                gamePanel.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(final View view, final MotionEvent motionEvent) {
//                Toast.makeText(getApplicationContext(), "Touch event received.",
//                        Toast.LENGTH_SHORT).show();

                        presenter.pressedScreen(motionEvent);
                        return true;
                    }
                });

                //create presenter
                presenter = new PresenterInGame(thisActivity, holder);
                //start the threads for the first time; after this, the threads will be started
                //and stopped through onResume() and onPause()

                //start the presenter's thread
                presenter.onResume();
                //start the view thread
//                thread = new PanelThread(holder, presenter.getLayer1(), presenter.getLayer2(), presenter.getLayer3()); //Start the thread that
//                thread = new PanelThread(holder, presenter.getLayers()); //Start the thread that
//                thread.setRunning(true);                                //will make calls to
//                thread.start();                                         //onDraw()

                ViewTreeObserver obs = gamePanel.getViewTreeObserver();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);
                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }
            }

        });

    }

    //part of default basic activity code
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ingame, menu);
        return true;
    }

    //credit to https://developer.android.com/training/appbar/actions.html#handle-actions
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_restart:
                // User chose the "Credits" item, show the app credits

                //http://stacktips.com/tutorials/android/android-toast-example
//                Toast.makeText(getApplicationContext(), "Restart pressed.",
//                        Toast.LENGTH_SHORT).show();

                presenter.pressedRestart();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public int getScreenWidth() {
         return screenX;
    }

    @Override
    public int getScreenHeight() {
        return screenY;
    }

    @Override
    public void restart() {
        //this will kill the presenter and create a new instance of it

        //first kill the thread
        presenter.onPause();
        //create a new presenter
        presenter = new PresenterInGame(thisActivity, holder);
        //start the presenter's thread
        presenter.onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();

//        gamePanel.setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()

        if (presenter != null) {
            //resume the presenter's thread
            presenter.onResume();
//            thread = new PanelThread(holder, presenter.getLayers()); //Start the thread that
//            thread.setRunning(true);                     //will make calls to
//            thread.start();                              //onDraw()
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        /*
            kill the thread if it's been created (it should be unless the app has been paused
            before the surfaceView has been displayed)
         */
        //if (thread != null) {
            //kill the presenter's thread too
        if (presenter != null) {
            presenter.onPause();
//            try {
//                thread.setRunning(false);                //Tells thread to stop
//                thread.join();                           //Removes thread from mem.
//            } catch (InterruptedException e) {
//            }
        }
    }

}
