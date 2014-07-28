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

import static android.util.Log.d;


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

    private View view;
    private EditText etPlayerNameSearch;
    private Button btnSearch;
    private ListView lvPlayerList;
    private String givenFirstName;
    private Player newPlayer;
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

        /* since we are creating the array within the inner class, this gives null pointer
        exception. I'm going to see creating the adapter within the inner class helps?
         */
      /*  aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
        lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
        lvPlayerList.setAdapter(aPlayerListAdapter);*/
        //aPlayerListAdapter.clear();

//        aPlayerListAdapter.notifyDataSetChanged();
        return view;
    }



    private void setup() {
        etPlayerNameSearch = (EditText) view.findViewById(R.id.etPlayerNameSearch);
        givenFirstName = etPlayerNameSearch.getText().toString();
        btnSearch = (Button) view.findViewById(R.id.btnSearch);

        /* TESTED UPDATES array of players within the done class, but not able to see
        outside of done method for adapter

        newPlayer = MySingleton.getInstance().newPlayer;
        arrayOfPlayers = MySingleton.getInstance().arrayOfPlayers; */



       // lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
       // arrayOfPlayers = MySingleton.getInstance().arrayOfPlayers;
      // aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
       // lvPlayerList.setAdapter(aPlayerListAdapter);

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


    private View.OnClickListener btnSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TRYINg to see if this helps to see the array of players available outside of done?
            // THis works tested.

           // newPlayer = MySingleton.getInstance().newPlayer;
           // arrayOfPlayers = MySingleton.getInstance().arrayOfPlayers;
           // aPlayerListAdapter = MySingleton.getInstance().aPlayerListAdapter;

            searchPlayer(givenFirstName);

            /* FIND OUT WHY? the array of players though it is declared as a global instance,
            it still doesn't show outside the anonymous class-The following code does not work ----
            TODO
            searchPlayer(givenFirstName);
            Log.d(TAG,"Printing the array list from search button");
            Log.d(TAG,"print the array list size  : " + arrayOfPlayers.size());
            Log.d(TAG,"PRINT THE FINAL LIST OF PLAYERS ");
            for (Player p:arrayOfPlayers) {
                Log.d(TAG,"print the array list of players from search button: " + p.getPlayerID());
            }*/


        }
    };

    public void searchPlayer(String playerFirstName)
    {

        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");

       // newPlayer = MySingleton.getInstance().newPlayer;
       // arrayOfPlayers = MySingleton.getInstance().arrayOfPlayers;
        // Making the adapter FINAL DID NOT WORK
       // final PlayerSearchListArrayAdapter aPlayerListAdapter =
       //         new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);

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

            @Override
            public void done(List<ParseObject> playerList, com.parse.ParseException e) {
               // aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
                if (e == null)
                {
                    d("TAG", "Retrieved: size =  " + playerList.size());

                    for(int i=0;i<playerList.size();i++)
                    {
                        newPlayer = MySingleton.getInstance().newPlayer;
                        arrayOfPlayers = MySingleton.getInstance().arrayOfPlayers;

                        // short name is abbreviated name; got it from Parse
                        // create a single player
                        d("TAG", "FirstName =  " + playerList.get(i).getString("FirstName"));
                        String shortName = playerList.get(i).getString("ShortName");
                        String firstName = playerList.get(i).getString("FirstName");
                        String lastName = playerList.get(i).getString("LastName");
                        playerID = playerList.get(i).getInt("PlayerID");

                        Log.d("Player", "short name: " + shortName);

                        newPlayer.setPlayerShortName(shortName);
                        newPlayer.setPlayerFirstName(firstName);
                        newPlayer.setPlayerLastName(lastName);
                        newPlayer.setPlayerID(playerID);
                        newPlayer.setPhotoUrl(playerList.get(i).getString("PhotoUrl"));

                        Log.d("Player", "the photo url = : " + newPlayer.getPhotoUrl());
                        newPlayer.setFantasyPosition(playerList.get(i).getString("FantasyPosition"));

                        if (newPlayer != null) {
                            d("Player", "adding the new player to arraylist ");
                            arrayOfPlayers.add(newPlayer);

                           Log.d("Player", "the new player's last name  in the arrayList " + arrayOfPlayers.get(i).getPlayerLastName());
                           Log.d("Player", "the new player's photo url  in the arrayList " + arrayOfPlayers.get(i).getPhotoUrl());

                        }
                        //printing the playerlist:
                        for(Player x:arrayOfPlayers) {
                            Log.d(TAG,"printing the list of player's last name within the for: " + x.getPlayerLastName());
                        }


                        lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
                        aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
                        lvPlayerList.setAdapter(aPlayerListAdapter);
                        aPlayerListAdapter.notifyDataSetChanged();

                    }// end of for

                    /* THIS WORKS WITHIN THE INNER CLASS --- TESTED)
                    Log.d("Player", "the array size " + arrayOfPlayers.size());
                    Log.d("Player", "the player id from the arrayList " + arrayOfPlayers.get(8).getPlayerID());
                    Log.d("Player"," the activity in the player fragment: " + getActivity());

                    */

                    // UNCOMMENT THIS LATER IF NECESSARY-----

                    // Adapter has to be within the inner class done, otherwise it does not
                    // get updated.

                   /*lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
                   lvPlayerList.setAdapter(aPlayerListAdapter);
                   aPlayerListAdapter.notifyDataSetChanged(); */

                }

                else
                {
                    d("score", "Error: " + e.getMessage());
                    //playerArrayList = null;

                }
            }// end of done

        }); // end of inner class

      // Log.d(TAG, "Printing playerArrayList after coming out of done : " + arrayOfPlayers.size());
       Log.d("Player", " the activity in the player fragment: " + getActivity());
    }

}
