package com.avalanche.hello.helloavalanche;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GestureDetector.OnGestureListener {

    private View currentView;

    private GestureDetector gestureDetector;

    private MediaPlayer bgMusic;
    private MediaPlayer flingFx;
    private MediaPlayer clickFx;
    private Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Load background sounds and music
        bgMusic = MediaPlayer.create(this, R.raw.bg_music);
        flingFx = MediaPlayer.create(this, R.raw.sfx_swish_1);

        bgMusic.setLooping(true);

        // Create a Runnable to play background music at a separate thread
        r = new Runnable() {
            @Override
            public void run() {
                bgMusic.start();
            }
        };

        gestureDetector = new GestureDetector(this, this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(r).start();
    }

    @Override
    protected void onDestroy() {
        if (bgMusic != null)
            bgMusic.stop();
        if (flingFx != null)
            flingFx.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_drawer_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int fragmentId = R.layout.fragment_default;

        if (currentView == null)
            currentView = findViewById(R.id.fragment_default);

        ViewGroup parent = (ViewGroup) currentView.getParent();

        int index = parent.indexOfChild(currentView);
        parent.removeView(currentView);

        if (id == R.id.nav_company) {
            fragmentId = R.layout.fragment_company;
        } else if (id == R.id.nav_people) {
            fragmentId = R.layout.fragment_people;
        } else if (id == R.id.nav_plan) {
            fragmentId = R.layout.fragment_plan;
        } else if (id == R.id.nav_about) {
            fragmentId = R.layout.fragment_about;
        } else {
            fragmentId = R.layout.fragment_default;
        }

        currentView = getLayoutInflater().inflate(fragmentId, parent, false);
        parent.addView(currentView, index);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        // Swipe Up
        if(motionEvent1.getY() - motionEvent2.getY() > 50){
            Toast.makeText(this, "Up", Toast.LENGTH_LONG);
            flingFx.start();
            return true;
        }
        // Swipe Down
        if(motionEvent2.getY() - motionEvent1.getY() > 50){
            Toast.makeText(this, "Down", Toast.LENGTH_LONG);
            flingFx.start();
            return true;
        }
        // Swipe Left
        if(motionEvent1.getX() - motionEvent2.getX() > 50){
            Toast.makeText(this, "Left", Toast.LENGTH_LONG);
            flingFx.start();
            return true;
        }
        // Swipe Right
        if(motionEvent2.getX() - motionEvent1.getX() > 50) {
            Toast.makeText(this, "Right", Toast.LENGTH_LONG);
            flingFx.start();
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

}
