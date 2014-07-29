package com.fantasysportsgroupproject.app.Fragment;

import android.app.Activity;
import android.content.Intent;
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

import com.fantasysportsgroupproject.app.AnnaActivity;
import com.fantasysportsgroupproject.app.Helpers.PlayerSearchListArrayAdapter;
import com.fantasysportsgroupproject.app.Model.Player;
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
    private static final int REQUEST_CODE = 100;

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

        return view;
    }

    private void setup() {
        etPlayerNameSearch = (EditText) view.findViewById(R.id.etPlayerNameSearch);
        givenFirstName = etPlayerNameSearch.getText().toString();
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
    }

    private void setupListener()
    {
        btnSearch.setOnClickListener(btnSearchListener);
        lvPlayerList.setOnItemClickListener(playerListListener);

       /* lvPlayerList.setOnClickListener(new AdapterView.OnClickListener(){


            @Override
            public void onItemClick(View v) {

            }
        }); */
    }


    // on click of the list, show the detailed page with stats for the player
    // send id, position to Anna
    // what if I don't have any result in my list?=======TODO????
   // private View.OnClickListener btnSearchListener = new View.OnClickListener()
   private  AdapterView.OnItemClickListener playerListListener = new AdapterView.OnItemClickListener()
   {
        @Override
       public void onItemClick(AdapterView<?> adapterView,View view, int position,long id)
        {

            Toast.makeText(getActivity(), "call anna activity and pass playerId ", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"the activity within player fragment: " + getActivity() );

            Player p =  arrayOfPlayers.get(position);

            int playerID = p.getPlayerID();
           // String fanPosition = p.getFantasyPosition();
            if (p != null)
            {
                 Intent i = new Intent(getActivity(),AnnaActivity.class);
                 i.putExtra("player_id ", playerID);
              //   i.putExtra("fantasy_position",fanPosition);
                 startActivityForResult(i, REQUEST_CODE);
            }
            else
               Toast.makeText(getActivity(),"NO player to show: Click on a player", Toast.LENGTH_SHORT).show();
        }
    };

        // add it to fav. screens activity based on the request code
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

            // REQUEST_CODE is defined above
          /*  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
                // add this new tweet to the timeline
                Toast.makeText(getActivity(),"create a new intent for fav screen: " , Toast.LENGTH_SHORT).show();

            }*/
         /*   if (data == null) return;
            Toast.makeText(getActivity(),"create a new intent for fav screen: " , Toast.LENGTH_SHORT).show();
        } */


        }

    private View.OnClickListener btnSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchPlayer(givenFirstName);
        }
    };

    public void searchPlayer(String playerFirstName)
    {
        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");

        try {
            query_players.whereMatches("FirstName","Patrick");
        } catch (NullPointerException e) {
            e.printStackTrace(); // change this to meaning ful message

        }

        query_players.findInBackground(new FindCallback<ParseObject>()
        {
            public String shortName,firstName,lastName;
            public int playerID;

            @Override
            public void done(List<ParseObject> playerList, com.parse.ParseException e) {

                arrayOfPlayers = new ArrayList<Player>();
                lvPlayerList = (ListView) view.findViewById(R.id.lvPlayerList);
                aPlayerListAdapter= new PlayerSearchListArrayAdapter(getActivity(),arrayOfPlayers);
                lvPlayerList.setAdapter(aPlayerListAdapter);

                if (e == null)
                {
                    Log.d("TAG", "Retrieved: size =  " + playerList.size());

                    for(int i=0;i<playerList.size();i++)
                    {
                        shortName = playerList.get(i).getString("ShortName");
                        firstName = playerList.get(i).getString("FirstName");
                        lastName = playerList.get(i).getString("LastName");
                        playerID = playerList.get(i).getInt("PlayerID");

                        // create a single player
                        newPlayer = new Player();
                        newPlayer.setPlayerShortName(shortName);
                        newPlayer.setPlayerFirstName(firstName);
                        newPlayer.setPlayerLastName(lastName);
                        newPlayer.setPlayerID(playerID);
                        newPlayer.setPhotoUrl(playerList.get(i).getString("PhotoUrl"));
                        newPlayer.setFantasyPosition(playerList.get(i).getString("FantasyPosition"));

                        if (newPlayer != null) {
                            arrayOfPlayers.add(newPlayer);
                        }
                    }// end of for
                    aPlayerListAdapter.notifyDataSetChanged();
                } //end of (e == null)
                else
                {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }// end of done

        }); // end of inner class

    }//end of searchPlayer()

}
