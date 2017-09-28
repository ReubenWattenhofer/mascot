package com.game.cis350.mascot;

//CHECKSTYLE:ON / OFF

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.game.cis350.mascot.interfaces.IPresenterMain;
import com.game.cis350.mascot.interfaces.IViewMain;

/**
 * This class is the main activity, and is the start point of the program.
 */
public class MainActivity extends AppCompatActivity implements IViewMain {

    /**
     * Handles the logic when stuff gets pressed/interacted with.
     */
    private IPresenterMain presenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up buttons
        Button play = (Button) findViewById(R.id.button);
        Button options = (Button) findViewById(R.id.button2);
        Button quit = (Button) findViewById(R.id.button3);

        //create presenter
        //TODO: inject presenter rather than instantiate one here??
        //(for easier testing)
        presenter = new PresenterMain(this);

        //set up listeners
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //pass the input off to the presenter
                //credit to https://antonioleiva.com/mvp-android for the design
                presenter.pressedPlay();
            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab =
//                (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                Snackbar.make(view, "Replace with your own action",
//                                Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(final Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(final MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void startGame() {
        //credit to https://developer.android.com/training/basics/firstapp/starting-activity.html
        //create the intent and fire it up
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void quitApp() {

    }

    @Override
    public void showOptions() {

    }
}
