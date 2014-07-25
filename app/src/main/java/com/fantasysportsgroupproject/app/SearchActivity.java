package com.fantasysportsgroupproject.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fantasysportsgroupproject.app.Fragment.TeamFragment;
import com.fantasysportsgroupproject.app.Helpers.FragmentTabListener;


//import com.fantasysportsgroupproject.app.R;

//import com.parse.R;

//import android.support.v4.app.FragmentActivity;

// This is the search activity with 2 tabs: and that calls player search fragment.

// yet to implement: on tab selected, select player fragment
// where to set the player fragment: player search part and list part fragment
//  you extend fragmentActivity  to support gingerbread
// Otherwise you can just extend activity
public class SearchActivity extends FragmentActivity{
    //public class SearchActivity extends ActionBarActivity {


    private static final String tag = "SEARCH_ACTIVITY";
    private static final String fragmentPlayerTag = "PLAYER";
    private static final String fragmentTeamTag = "PLAYER";
    private final String FIRST_TAB_TAG = "Team";
    private final String SECOND_TAB_TAG = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabs();
        setContentView(R.layout.activity_search);
       // setupTabs();

       // This if savedInstanceState is to check if the fragment have been instantiated before
       // if this is created for the first time, re-initialize the fragment here.
       if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer,new PlayerFragment(),fragmentPlayerTag);
            ft.commit();
       }
            // Add the three fragments for the main screen
        /*    getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_map, MapFragment.newInstance(getGoogleMapOptions()))
                    .add(R.id.fragment_container_middle, new NearestStationFragment())
                    .add(R.id.fragment_container_bottom, new FavoriteStationsGridFragment())
                    .commit();

        } */
       // PlayerFragment pFragment = (PlayerFragment) getSupportFragmentManager().
       //         findFragmentById(R.id.pFrgament);
        //setupNetwork(); // for player
        Log.d(tag, "Search activity screen success");
    }

    // Note: you don't have to do xml for tabs
    private void setupTabs() {
        //ActionBar actionBar = getSupportActionBar(); this is for gingerbread support
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        // create new tabs and set up the titles of the tabs
        ActionBar.Tab mTeamTab = actionBar
                .newTab()
                .setText("By Team")
                .setTag("TeamFragment");

        ActionBar.Tab mPlayerTab = actionBar
                .newTab()
                .setText("By Player")
                .setTag("PlayerFragment");

        //bind fragments to the tabs - set up Listener for each tab
        mPlayerTab.setTabListener(
                (ActionBar.TabListener) new FragmentTabListener<PlayerFragment>(R.id.flContainer, this, fragmentPlayerTag, PlayerFragment.class));
        mTeamTab.setTabListener(
                (ActionBar.TabListener) new FragmentTabListener<TeamFragment>(R.id.flContainer, this, fragmentTeamTag, TeamFragment.class));


        // add the tabs to action bar
        actionBar.addTab(mTeamTab);
        actionBar.addTab(mPlayerTab);
        actionBar.selectTab(mPlayerTab); // default


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
