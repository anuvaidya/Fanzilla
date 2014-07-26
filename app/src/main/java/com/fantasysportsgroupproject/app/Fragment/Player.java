package com.fantasysportsgroupproject.app.Fragment;

import android.util.Log;

/**
 * Created by Anu on 7/19/14.
 *
 */

// This class is a player model that has player info and also a static function to search for a
// given player.
//@ParseClassName("Player")
public class Player {


    public static final String TAG = Player.class.getSimpleName();

    private int playerID;
    private String playerShortName; //B.Jones

    private String fantasyPosition; // QB etc

    private String playerFirstName;
    private String playerLastName;
    private String photoUrl;

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

    public void setPlayerID(int playerID){
        this.playerID = playerID;

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

        Log.d(TAG,"inside the to_string method: print the array list of players");
        String string = "player_id: " + playerID + "player_first name: " + playerFirstName;
        return string;
    }


}

