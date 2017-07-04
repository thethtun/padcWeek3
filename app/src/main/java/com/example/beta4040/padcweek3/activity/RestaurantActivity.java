package com.example.beta4040.padcweek3.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beta4040.padcweek3.R;
import com.example.beta4040.padcweek3.adapter.RecyclerViewAdapter;
import com.example.beta4040.padcweek3.data.Persistent.RestaurantContract;
import com.example.beta4040.padcweek3.data.retrofit.RetrofitDataAgent;
import com.example.beta4040.padcweek3.data.vos.RestaurantVO;
import com.example.beta4040.padcweek3.event.RetrofitResponseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = RestaurantActivity.class.getSimpleName();
    private int RESTAURANT_LOADER_ID = 100;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter = null;
    TextView restaurantCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onActivityTriggered();
        View app_bar_main = findViewById(R.id.app_bar_main);
        View content_main = app_bar_main.findViewById(R.id.content_main);
        restaurantCount = content_main.findViewById(R.id.restaurant_count);
        recyclerView = content_main.findViewById(R.id.recycler);
//        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        EventBus.getDefault().register(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        RetrofitDataAgent.getObjInstance().fetchRestaurantData();

        Log.d("recycler adapter", String.valueOf(adapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportLoaderManager().initLoader(RESTAURANT_LOADER_ID, null, this);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void inflateRecyclerViewData(RetrofitResponseEvent.RastaurantsResponseData event){
//        adapter = new RecyclerViewAdapter(event.getRestaurants());
//        adapter = RecyclerViewAdapter.getInstace(event.getRestaurants());
//        Log.d("EventBus Data", String.valueOf(event.getRestaurants()));
//        recyclerView.setAdapter(adapter);
        restaurantCount.setText(String.valueOf(event.getRestaurants().size()) + " Restaurants Delivers to you.");
//        adapter.notifyDataSetChanged();
        RestaurantVO.saveRestaurantsIntoDatabase(event.getRestaurants(), getApplicationContext());
    }

    @Override
    public void onActivityTriggered() {
        Toast.makeText(this, RestaurantActivity.class.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getApplicationContext(),
                RestaurantContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RestaurantVO> restaurantVOs = new ArrayList<>();
        if(data!=null && data.moveToFirst()){
            do{
                RestaurantVO restaurantVO = RestaurantVO.parseFromContentValues(data);
                restaurantVOs.add(restaurantVO);
            }while(data.moveToNext());
        }
        Log.d("RestaurantVO_COUNT", String.valueOf(restaurantVOs.size()));
        adapter = RecyclerViewAdapter.getInstace(restaurantVOs);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}


