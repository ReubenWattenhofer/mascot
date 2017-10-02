package com.game.cis350.mascot.views;

import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.game.cis350.mascot.R;
import com.game.cis350.mascot.interfaces.presenters.IPresenterInGame;
import com.game.cis350.mascot.interfaces.views.IViewGame;
import com.game.cis350.mascot.presenters.PresenterInGame;
//import android.support.v7.widget.Toolbar;
//import android.view.View;



/**
 * This class is the View (MVP View, not Android View) for the game.
 * @author Reuben 9/25/2017
 */

public class GameActivity extends AppCompatActivity implements IViewGame {

    /**
     * Handles the logic when stuff gets pressed/interacted with.
     */
    private IPresenterInGame presenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);

        //set the toolbar as the activity's app bar
        //credit to https://developer.android.com/training/appbar/setting-up.html#add-toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mascot");

        //create presenter
        presenter = new PresenterInGame(this);


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
}
