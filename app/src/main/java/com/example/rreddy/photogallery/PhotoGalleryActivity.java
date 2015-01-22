package com.example.rreddy.photogallery;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class PhotoGalleryActivity extends SingleFragmentActivity { // ActionBarActivity

    /* this whole thing needs to be removed as createFragment gets called as onCreate() of Activity
    @Override
    public void onCreate(Bundle savedInstanceState) { // default method created by AndroidStudio
    // which gets called when an Activity is created (Activity with LAUNCHER Intent gets created on app-launch
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_photo_gallery); // setContentView() changes the view and will lead to a crash with "no view to load the Fragment"
    } */

    @Override
    protected Fragment createFragment() {
        return new PhotoGalleryFragment();
    }


    // auto-generated code for the Actionbar Menu and its default "Settings" item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_gallery, menu);
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
}
