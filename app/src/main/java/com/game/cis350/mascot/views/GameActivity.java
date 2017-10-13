package com.game.cis350.mascot.views;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.game.cis350.mascot.R;
import com.game.cis350.mascot.interfaces.IImage;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.presenters.PresenterInGame;

import java.util.ArrayList;
//import android.support.v7.widget.Toolbar;
//import android.view.View;



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
     * used to initialize the presenter
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


////        screenY = size.y - toolbar.getHeight();
//
//        // Calculate ActionBar's height
//        //credit https://stackoverflow.com/questions/13833582/get-the-height-of-actionbar
//        TypedValue tv = new TypedValue();
//        int actionBarHeight = 0;
//        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
//        }
//        screenX = size.x;
//        screenY = size.y - actionBarHeight;
//        //This will not result in a perfect height, but will be close enough; the app won't crash
//        //if you try to draw offscreen.



        // Initialize gameView and set it as the view
//        gamePanel = new DrawingPanel(this); //, size.x, size.y);
        gamePanel = (SurfaceView) findViewById(R.id.surfaceView);
        holder = gamePanel.getHolder();

//        gamePanel.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
//        screenX = gamePanel.getMeasuredWidth(); // view width
//        screenY = gamePanel.getMeasuredHeight(); //view height

        //get layout and add the game panel to it
        //https://stackoverflow.com/questions/27128425/add-multiple-custom-views-to-layout-programmatically
//        ViewGroup layout = (ViewGroup) findViewById(R.id.linear_layout);


//        layout.addView(gamePanel);


//        gamePanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                gamePanel.getViewTreeObserver().removeOnPreDrawListener(this);
//                screenX = gamePanel.getWidth();
//                screenY = gamePanel.getHeight();
//                return true;
//            }
//        });

        //credit https://stackoverflow.com/questions/4142090/how-to-retrieve-the-dimensions-of-a-view
        ViewTreeObserver vto = gamePanel.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
//                LayerDrawable ld = (LayerDrawable) gamePanel.getBackground();
//                ld.setLayerInset(1, 0, gamePanel.getHeight() / 2, 0, 0);
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
                presenter = new PresenterInGame(thisActivity);

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
                Toast.makeText(getApplicationContext(), "Restart pressed.",
                        Toast.LENGTH_SHORT).show();

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
//        gamePanel.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        return gamePanel.getMeasuredWidth();

//        if (!holder.getSurface().isValid()){
//            return 0;
//        }
//        Canvas c = holder.lockCanvas();
//        return c.getWidth();
         return screenX; //(thread != null) ? thread.getScreenWidth() : 0;
    }

    @Override
    public int getScreenHeight() {
//        gamePanel.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        return gamePanel.getMeasuredHeight();

//        if (!holder.getSurface().isValid()){
//            return 0;
//        }
//        Canvas canvas = holder.lockCanvas();
//        canvas.post(new Runnable()
//                    {
//                        public void run()
//                        {
//                            int w = canvas.getWidth();
//                        }
//                    }
//        );
//        return c.getHeight();
        return screenY; // (thread != null) ? thread.getScreenHeight() : 0;
    }

    @Override
    public void update(final ArrayList<IImage> images) {
        thread.update(images);
    }

//    public void update(final Point p) {
//        thread.update(p);
//    }


    @Override
    protected void onResume() {
        super.onResume();

//        gamePanel.setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()


        thread = new PanelThread(holder); //, gamePanel); //Start the thread that
        thread.setRunning(true);                     //will make calls to
        thread.start();                              //onDraw()
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            thread.setRunning(false);                //Tells thread to stop
            thread.join();                           //Removes thread from mem.
        } catch (InterruptedException e) { }
    }

}
