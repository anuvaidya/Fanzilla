package com.fantasysportsgroupproject.app.Fragment;

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

import com.fantasysportsgroupproject.app.Helpers.MySingleton;
import com.fantasysportsgroupproject.app.Helpers.PlayerSearchListArrayAdapter;
import com.fantasysportsgroupproject.app.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anu on 7/17/14.
 */

// This player Fragment is called from SearchActivity
// This player fragment contains 2 parts: search part and the middle screen contain the list
// from the search
// list will have: Player Image, Player Full Name , Player position
// Adapter to display player list



public class PlayerFragment extends Fragment{

    public static final String TAG = "PLAYER.CLASS";

    private Player newPlayer;


    private View view;
    private EditText etPlayerNameSearch;
    private Button btnSearch;
    private ListView lvPlayerList;
    private String givenFirstName;
    private ArrayList<Player> arrayOfPlayers;
    private PlayerSearchListArrayAdapter aPlayerListAdapter; // to show the list


    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity instance.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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

        aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
        lvPlayerList.setAdapter(aPlayerListAdapter);

        return view;
    }



    private void setup() {
        etPlayerNameSearch = (EditText) view.findViewById(R.id.etPlayerNameSearch);
        givenFirstName = etPlayerNameSearch.getText().toString();
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
        arrayOfPlayers = new ArrayList<Player>();



    }





    private void setupListener()
    {
        btnSearch.setOnClickListener(btnSearchListener);

        //lvPlayerList.setOnItemClickListener(playerListListener);
    }

    // on click of the list, show the detailed page with stats for the player
    // send id, position to Anna
    // what if I don't have any result in my list?=======TODO????
    private AdapterView.OnItemClickListener playerListListener = new AdapterView.OnItemClickListener() {
        @Override
       public void onItemClick(AdapterView<?> adapterView,View view, int position,long id) {
            // create a bundle for args
            // pass player id to Anna



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

            newPlayer = MySingleton.getInstance().newPlayer;
            arrayOfPlayers = searchPlayer(givenFirstName);
            Log.d(TAG,"print the array list size  : " + arrayOfPlayers.size());
            Log.d(TAG,"PRINT THE FINAL LIST OF PLAYERS ");
            for (Player p:arrayOfPlayers) {
                Log.d(TAG,"print the array list of players: " + p.toString());
            }
        }
    };

    public ArrayList<Player> searchPlayer(String playerFirstName)
    {

        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");
        try {
            // query_players.whereEqualTo("FirstName", playerFirstName); // change to where matches
            query_players.whereMatches("FirstName","Patrick");
        } catch (NullPointerException e) {
            e.printStackTrace(); // change this to meaning ful message

        }

        //playerArrayList.add(null);
        query_players.findInBackground(new FindCallback<ParseObject>()
        {
            public String shortName;
            public int playerID;

            // create a new playerArrayList of Type Player and add it to list of players
            @Override
            public void done(List<ParseObject> playerList, com.parse.ParseException e) {
                if (e == null) // changed from == null to !=null question: will this check for null pointer exception on list?
                {
                    Log.d("TAG", "Retrieved: size =  " + playerList.size());

                    for(int i=0;i<playerList.size();i++)
                    {
                        // short name is abbreviated name; got it from Parse
                        // create a single player
                        Log.d("TAG", "FirstName =  " + playerList.get(i).getString("FirstName"));
                        String shortName = playerList.get(i).getString("ShortName");
                        // Toast.makeText(g,"shortName = ",Toast.LENGTH_SHORT).show();
                        Log.d("Player", "short name: " +shortName);
                        //newPlayer = new Player();
                        // if (newPlayer != null) {
                        newPlayer.setPlayerShortName(shortName);
                        playerID = playerList.get(i).getInt("PlayerID");
                        newPlayer.setPlayerID(playerID);
                        newPlayer.setPhotoUrl(playerList.get(i).getString("PhotoUrl"));
                        newPlayer.setFantasyPosition(playerList.get(i).getString("FantasyPosition"));

                        if (newPlayer != null) {
                            Log.d("Player", "adding the new player to arraylist ");
                            arrayOfPlayers.add(newPlayer);
                            Log.d("Player", "the new player is " + arrayOfPlayers.toString());
                        }
                            // add the player to the array list of players */
                        Log.d("TAG", "Short Name of the player  : " + newPlayer.getPlayerShortName() + " with id : " + newPlayer.getPlayerID() + "\n") ;
                        //  Log.d("TAG", "the new added player to the list" + playerArrayList.get(i).toString());
                    }
                }

                else
                {
                    Log.d("score", "Error: " + e.getMessage());
                    //playerArrayList = null;

                }


            }// end of done


        }); // end of inner class

        // Log.d(TAG,"Printing playerArrayList size : "+playerArrayList.size());
        return arrayOfPlayers;
        // return newPlayer;
    }

}
