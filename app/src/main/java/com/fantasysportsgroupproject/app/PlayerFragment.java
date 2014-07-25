package com.fantasysportsgroupproject.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fantasysportsgroupproject.app.Model.Player;

import java.util.ArrayList;





/**
 * Created by Anu on 7/17/14.
 */

// This player Fragment is called from SearchActivity
// This player fragment contains 2 parts: search part and the middle screen contain the list
// from the search
// list will have: Player Image, Player Full Name , Player position
// Adapter to display player list
public class PlayerFragment extends Fragment{

    public final String TAG = "PLAYER.CLASS";


    private View view;
    private EditText etPlayerNameSearch;
    private Button btnSearch;
    private ListView lvPlayerList;
    private String givenFirstName;
    private ArrayList<Player> arrayOfPlayers;
  //  private PlayerSearchListArrayAdapter aPlayerListAdapter; // to show the list

    private Player newPlayer;

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity instance.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //this.l = (SearchActivity) activity;
    }

    public PlayerFragment(){
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        view = inflater.inflate(R.layout.fragment_player, container, false);
        setup();
        setupListener();
        return view;
    }



    private void setup() {
        etPlayerNameSearch = (EditText) view.findViewById(R.id.etPlayerNameSearch);
        givenFirstName = etPlayerNameSearch.getText().toString();
        btnSearch = (Button) view.findViewById(R.id.btnSearch);

        /* uncomment later
        lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
        arrayOfPlayers = new ArrayList<Player>();
        aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);

        lvPlayerList.setAdapter(aPlayerListAdapter); */


    }





    private void setupListener()
    {
        btnSearch.setOnClickListener(btnSearchListener);
       // lvPlayerList.setOnItemClickListener(playerListListener);
    }

    // on click of the list, show the detailed page with stats for the player
    // send id, position to Anna
    // what if I don't have any result in my list?=======TODO????
    private AdapterView.OnItemClickListener playerListListener = new AdapterView.OnItemClickListener() {
        @Override
       public void onItemClick(AdapterView<?> adapterView,View view, int position,long id) {
            // create a bundle for args
            // pass player id to Anna


            Player newPlayer = new Player();
            // How to get the item at that position
            //getItem(position);
            Toast.makeText(getActivity(),"On Click list - activate detailed player activity", Toast.LENGTH_SHORT).show();
            // uncomment later =========   todo????
           /* Intent i = new Intent(DetailedPlayerListActivity.class,"Showing Stats");
            i.putInt(newPlayer.getPlayerID());
            i.putString(newPlayer.getFantasyPosition()); */

            //startActivityForResult(intent, DISPLAY_STATS_REQUEST_CODE);
            // After passing the player id to Activity, DO I need result from Anna


        }

    };


    // On click of search button, call the static fun with the given first name
    //
    private View.OnClickListener btnSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           Player newPlayer = new Player();
          //  arrayOfPlayers = player.searchPlayer(givenFirstName);
          // arrayOfPlayers = Player.searchPlayer(givenFirstName);
            newPlayer.searchPlayer(givenFirstName,newPlayer);
            Log.d(TAG,newPlayer.getPlayerShortName());

            //player.searchPlayer(givenFirstName);


        }
    };

}
