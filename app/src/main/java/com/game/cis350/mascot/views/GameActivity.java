package com.game.cis350.mascot.views;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
//import android.widget.Toast;

import com.game.cis350.mascot.OnSwipeTouchListener;
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

    /**
     * Popup window for winning and losing.
     */
    private PopupWindow winWindow, loseWindow;

    /**
     * Needed for the popup windows.
     */
    private Context mContext;

    /**
     * Decides which type of input to receive.
     */
    private boolean tapToMove;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        thisActivity = this;

        //read move setting from bundle
        //credit to https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        Bundle extras = getIntent().getExtras();
        tapToMove = extras.getBoolean("moveType");

        //lock the screen to portrait mode
        //credit https://stackoverflow.com/questions/2366706/how-to-lock-orientation-during-runtime
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);

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

        // Get the application context
        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html
        mContext = getApplicationContext();

        //credit https://stackoverflow.com/questions/4142090/how-to-retrieve-the-dimensions-of-a-view
        ViewTreeObserver vto = gamePanel.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                screenX = gamePanel.getWidth();
                screenY = gamePanel.getHeight();

                //add touch listener to game panel
//                gamePanel.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(final View view, final MotionEvent motionEvent) {
////                Toast.makeText(getApplicationContext(), "Touch event received.",
////                        Toast.LENGTH_SHORT).show();
//
//                        presenter.pressedScreen(motionEvent);
//                        return true;
//                    }
//                });
                //credit to https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
                gamePanel.setOnTouchListener(new OnSwipeTouchListener(thisActivity) {
                    @Override
                    public void onSwipeTop() {
                        if (!tapToMove) {
                            presenter.swipedUp();
                        }
//                        Toast.makeText(thisActivity, "top", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSwipeRight() {
                        if (!tapToMove) {
                            presenter.swipedRight();
                        }
//                        Toast.makeText(thisActivity, "right", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSwipeLeft() {
                        if (!tapToMove) {
                            presenter.swipedLeft();
                        }
//                        Toast.makeText(thisActivity, "left", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSwipeBottom() {
                        if (!tapToMove) {
                            presenter.swipedDown();
                        }
//                        Toast.makeText(thisActivity, "bottom", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void down(final MotionEvent e) {
                        if (tapToMove) {
                            presenter.pressedScreen(e);
                        }
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
    public void showWin() {
        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        final View view = inflater.inflate(R.layout.win, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        // Initialize a new instance of popup window
        winWindow = new PopupWindow(
                view,
                width,
                height,
                focusable
        );

        //credit to https://stackoverflow.com/questions/9247792/how-to-make-animation-for-popup-window-in-android
        winWindow.setAnimationStyle(R.style.AnimationPopup);

        winWindow.setOutsideTouchable(false);
        winWindow.setFocusable(true);
        winWindow.setTouchable(true);


        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            winWindow.setElevation(5.0f);
        }

        // Finally, show the popup window at the center location of root relative layout
        //credit to https://www.codota.com/android/methods/android.widget.PopupWindow/showAtLocation
        //and https://stackoverflow.com/questions/15862052/get-the-measures-of-popup-window
        int[] coords = new int[2];


        gamePanel.getLocationInWindow(coords);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        int mContainerPositionX = coords[0] + screenX / 2 - view.getMeasuredWidth() / 2;
        int mContainerPositionY = coords[1] + screenY / 2 - view.getMeasuredHeight() / 2;
        winWindow.showAtLocation(gamePanel, 0, mContainerPositionX, mContainerPositionY);
        //credit to https://stackoverflow.com/questions/24935455/dont-dismiss-popupwindow-when-clicking-outside-only-when-the-close-button-is-c

        // credit to https://www.youtube.com/watch?v=wxqgtEewdfo
        //this will close the popup window when the screen is pressed
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(final View view, final MotionEvent motionEvent) {
//                winWindow.dismiss();
//                return true;
//            }
//        });
        Button restart = (Button) view.findViewById(R.id.restart);
        Button quit = (Button) view.findViewById(R.id.quit);

        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                presenter.pressedRestart();
                return true;
            }
        });

        quit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                presenter.pressedQuit();
                return true;
            }
        });

    }

    @Override
    public void showLose() {
        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        final View view = inflater.inflate(R.layout.lose, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        // Initialize a new instance of popup window
        loseWindow = new PopupWindow(
                view,
                width,
                height,
                focusable
        );

        //credit to https://stackoverflow.com/questions/9247792/how-to-make-animation-for-popup-window-in-android
        loseWindow.setAnimationStyle(R.style.AnimationPopup);

        //credit to https://stackoverflow.com/questions/24935455/dont-dismiss-popupwindow-when-clicking-outside-only-when-the-close-button-is-c
        loseWindow.setOutsideTouchable(false);
        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            loseWindow.setElevation(5.0f);
        }

        // Finally, show the popup window at the center location of root relative layout
        //credit to https://www.codota.com/android/methods/android.widget.PopupWindow/showAtLocation
        //and https://stackoverflow.com/questions/15862052/get-the-measures-of-popup-window
        int[] coords = new int[2];


        gamePanel.getLocationInWindow(coords);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        int mContainerPositionX = coords[0] + screenX / 2 - view.getMeasuredWidth() / 2;
        int mContainerPositionY = coords[1] + screenY / 2 - view.getMeasuredHeight() / 2;
        loseWindow.showAtLocation(gamePanel, 0, mContainerPositionX, mContainerPositionY);

        // credit to https://www.youtube.com/watch?v=wxqgtEewdfo
        //this will close the popup window when the screen is pressed
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(final View view, final MotionEvent motionEvent) {
//                loseWindow.dismiss();
//                return true;
//            }
//        });
        Button restart = (Button) view.findViewById(R.id.restart);
        Button quit = (Button) view.findViewById(R.id.quit);

        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                presenter.pressedRestart();
                return true;
            }
        });

        quit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                presenter.pressedQuit();
                return true;
            }
        });

    }


    @Override
    public void dismissWindows() {
        if (winWindow != null) {
            winWindow.dismiss();
        }
        if (loseWindow != null) {
            loseWindow.dismiss();
        }
    }

    @Override
    public void quit() {
        //we're done here
        finish();
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
