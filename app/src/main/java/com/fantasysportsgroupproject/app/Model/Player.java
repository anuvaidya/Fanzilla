package com.fantasysportsgroupproject.app.Model;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
/**
 * Created by Anu on 7/19/14.
 *
 */

// This class is a player model that has player info and also a static function to search for a
// given player.
//@ParseClassName("Player")
public class Player extends ParseObject{



  // private ArrayList<Player> playerArrayList = new ArrayList<Player>();


    public static final String TAG = Player.class.getSimpleName();

    private int playerID;
    private String playerShortName; //B.Jones

    private String fantasyPosition; // QB etc
    private String playerFirstName;
    private String playerLastName;
    private String photoUrl;
   // Player newPlayer = new Player();

  public Player()
    {

    }


    // constructor
   /* public Player()
    {
        super();
        this.playerID = 0;
        this.playerShortName=null;
        this.fantasyPosition=null;
    }*/


    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerShortName() {
        return playerShortName;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }


    public String getFantasyPosition() {
        return fantasyPosition;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int setPlayerID(int playerID){
        this.playerID = playerID;
        return playerID;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPlayerShortName(String playerShortName) {
        this.playerShortName = playerShortName;
    }

    public void setFantasyPosition(String fantasyPosition) {
        this.fantasyPosition = fantasyPosition;
    }
    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    // this method is needed to print the player details from the arraylist
    public String toString()
    {

        String string = "player_id: " + playerID + "player_first name: " + playerFirstName;
        return string;
    }
/*
   //public  ArrayList<Player> searchPlayer(String playerFirstName) {
  public static ArrayList<Player> searchPlayer(String playerFirstName) {
  //  public void searchPlayer(String playerFirstName) {

        //Player newPlayer;

        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");
        try {
       // query_players.whereEqualTo("FirstName", playerFirstName); // change to where matches
        query_players.whereMatches("FirstName","Patrick");
        } catch (NullPointerException e) {
            e.printStackTrace(); // change this to meaning ful message

        }

        // QUESTION: VALUE WITHIN THE INNER CLASS FINDCALLBACK WILL NEVER CHANGE:
        // IS THIS STATEMENT TRUE???

        final ArrayList<Player> playerArrayList = new ArrayList<Player>();

        //playerArrayList.add(null);
        query_players.findInBackground(new FindCallback<ParseObject>()
        {
            public String shortName;
            public int playerID;
            Player newPlayer;


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
                        Log.d("TAG", "FirstName =  " + playerList.get(0).getString("FirstName"));
                        String shortName = playerList.get(0).getString("ShortName");
                       // Toast.makeText(g,"shortName = ",Toast.LENGTH_SHORT).show();
                        Log.d("Player", "short name: " +shortName);
                       // newPlayer = new Player();
                       // if (newPlayer != null) {
                        newPlayer.setPlayerShortName(shortName);
                        playerID = playerList.get(i).getInt("PlayerID");
                        newPlayer.setPlayerID(playerID);
                        newPlayer.setPhotoUrl(playerList.get(i).getString("PhotoUrl"));
                        newPlayer.setFantasyPosition(playerList.get(i).getString("FantasyPosition"));

                              if (newPlayer != null)
                                    playerArrayList.add(newPlayer); // add the player to the array list of players
                        Log.d("TAG", "Short Name of the player  : " + shortName + " with id : " + playerID + "\n") ;
                      Log.d("TAG", "the new added player to the list" + playerArrayList.get(i).toString());
                        }
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    //playerArrayList = null;

                }


            }// end of done


        });

       // Log.d(TAG,"Printing playerArrayList size : "+playerArrayList.size());
        return playerArrayList;
    }*/

    //public  ArrayList<Player> searchPlayer(String playerFirstName) {

    public void searchPlayer(String playerFirstName,Player newPlayer)
    {



        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");
        try {
            // query_players.whereEqualTo("FirstName", playerFirstName); // change to where matches
            query_players.whereMatches("FirstName","Patrick");
        } catch (NullPointerException e) {
            e.printStackTrace(); // change this to meaning ful message

        }

        // QUESTION: VALUE WITHIN THE INNER CLASS FINDCALLBACK WILL NEVER CHANGE:
        // IS THIS STATEMENT TRUE???

        //final ArrayList<Player> playerArrayList = new ArrayList<Player>();

        //playerArrayList.add(null);
        query_players.findInBackground(new FindCallback<ParseObject>()
        {
            public String shortName;
            public int playerID;
           // Player newPlayer;


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
                        Log.d("TAG", "FirstName =  " + playerList.get(0).getString("FirstName"));
                        String shortName = playerList.get(0).getString("ShortName");
                        // Toast.makeText(g,"shortName = ",Toast.LENGTH_SHORT).show();
                        Log.d("Player", "short name: " +shortName);
                        //newPlayer = new Player();
                        // if (newPlayer != null) {
                        newPlayer.setPlayerShortName(shortName);
                        playerID = playerList.get(i).getInt("PlayerID");
                        newPlayer.setPlayerID(playerID);
                        newPlayer.setPhotoUrl(playerList.get(i).getString("PhotoUrl"));
                        newPlayer.setFantasyPosition(playerList.get(i).getString("FantasyPosition"));

                      /*  if (newPlayer != null)
                            playerArrayList.add(newPlayer); // add the player to the array list of players */
                        Log.d("TAG", "Short Name of the player  : " + shortName + " with id : " + playerID + "\n") ;
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
  //  return playerArrayList;
       // return newPlayer;
    }
}

