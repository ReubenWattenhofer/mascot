package com.game.cis350.mascot.views;

//CHECKSTYLE:ON / OFF

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.CoordinatorLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.game.cis350.mascot.presenters.PresenterMain;
import com.game.cis350.mascot.R;
import com.game.cis350.mascot.interfaces.presenters.IPresenterMain;
import com.game.cis350.mascot.interfaces.views.IViewMain;

/**
 * This class is the main activity and view, and is the start point of the program.
 */
public class MainActivity extends AppCompatActivity implements IViewMain {

    /**
     * Handles the logic when stuff gets pressed/interacted with.
     */
    private IPresenterMain presenter;

    /**
     * Needed for the popup credits window.
     */
    private Context mContext;

    /**
     * Credits popup window.
     */
    private PopupWindow creditsWindow;

    /**
     * Main layout, used to anchor popup window.
     */
    private View mainLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //lock the screen to portrait mode
        //credit https://stackoverflow.com/questions/2366706/how-to-lock-orientation-during-runtime
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //initialize the main layout
        //credit to https://stackoverflow.com/questions/38987442/how-to-make-a-simple-android-popup-window
        setContentView(R.layout.activity_main);
//        mainLayout = findViewById(android.R.id.toolbar2);
        mainLayout = findViewById(R.id.toolbar2);

        //set up buttons
        Button play = (Button) findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        //set the toolbar as the activity's app bar
        //credit to https://developer.android.com/training/appbar/setting-up.html#add-toolbar
        setSupportActionBar(toolbar);

        //create presenter
        /*
         It would be preferable to inject the presenter, but that's not possible using the Android
         framework.  Since the presenter is initialized here, it can't be mocked during testing.
         The solution is to test the presenter first by mocking; that way we know the
         presenter is safe when we begin testing this class.  Credit to Professor Nandigam for this
         explanation.
        */
        presenter = new PresenterMain(this);

        // Get the application context
        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html
        mContext = getApplicationContext();


        //set up listeners
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //pass the input off to the presenter
                //credit to https://antonioleiva.com/mvp-android for the design
                presenter.pressedPlay();
            }
        });
    }

    //part of default basic activity code
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //credit to https://developer.android.com/training/appbar/actions.html#handle-actions
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credits:
                // User chose the "Credits" item, show the app credits

                presenter.pressedCredits();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    public void startGame() {
        //credit to https://developer.android.com/training/basics/firstapp/starting-activity.html
        //create the intent and fire it up
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCredits() {

        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View creditsView = inflater.inflate(R.layout.credits, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        // Initialize a new instance of popup window
        creditsWindow = new PopupWindow(
                creditsView,
                width,
                height,
                focusable
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            creditsWindow.setElevation(5.0f);
        }

        // Finally, show the popup window at the center location of root relative layout
        //credit to https://www.codota.com/android/methods/android.widget.PopupWindow/showAtLocation
        //and https://stackoverflow.com/questions/15862052/get-the-measures-of-popup-window
        int[] coords = new int[2];

        mainLayout.getLocationInWindow(coords);

        creditsView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        int mContainerPositionX = coords[0] + 5; // + mainLayout.getWidth() - creditsView.getMeasuredWidth();
        int mContainerPositionY = coords[1] + mainLayout.getHeight();
        creditsWindow.showAtLocation(mainLayout, 0, mContainerPositionX, mContainerPositionY);

    }

    @Override
    public void closeCredits() {
        // Dismiss the popup window
        //credit to https://android--code.blogspot.com/2016/01/android-popup-window-example.html
        creditsWindow.dismiss();

    }


}
