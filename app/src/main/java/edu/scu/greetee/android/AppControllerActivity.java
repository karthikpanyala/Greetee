package edu.scu.greetee.android;

import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.scu.greetee.android.adapters.NavDrawerAdapter;
import edu.scu.greetee.android.model.NavDrawerItem;

public class AppControllerActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private Integer[] navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerAdapter Adapter;
    private TextView homeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_controller);
        initUIandDrawer();
        homeText=(TextView)findViewById(R.id.hometext);

        /// testing volley library
        String url = "http://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=1&appid=44db6a862fba0b067b1930da0d769e98";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray arr = response.getJSONArray("list");
                            homeText.setText(arr.getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);

    }

    // TODO as of now drawer is used. Planning to switch to menu.
    private void initUIandDrawer() {
        mTitle = mDrawerTitle = getTitle();
        // load slide menu items
        navMenuTitles = new String[]{"First Menu","Second Menu"};
        // nav drawer icons from resources
        navMenuIcons = new Integer[]{R.drawable.ic_menu_icon,R.drawable.ic_menu_icon};

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout.setDrawerShadow(R.drawable.abc_ic_clear_mtrl_alpha, GravityCompat.END);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        //adding items to menu
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons[0]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons[1]));

        // setting the nav drawer list adapter
        Adapter = new NavDrawerAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(Adapter);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.drawer_open,R.string.drawer_close){

            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.openDrawer(mDrawerList);
    }
}
