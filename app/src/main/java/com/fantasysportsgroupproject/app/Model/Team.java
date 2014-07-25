package com.fantasysportsgroupproject.app.Model;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by Anu on 7/19/14.
 */
public class Team {
    private String name;
    private String fullName;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    // constructing a single team name from JSON object
    // make sure in the get method, the string matches the one on the url.
    public static Team fromJsonObject(JSONObject jsonObject)
    {
        Team team = new Team();
        try {
            team.name =jsonObject.getString("name");
            team.fullName = jsonObject.getString("Full Name");

        }catch(Exception e)
        {
            Log.d("error","Error getting team Name: " + e.getMessage());
            return null;
        }
        return team;
    }


    // CHANGE THIS METHOD NAME ---- TODO()
    // ISSUES: HAD ERROR ON FIND CALL BACK,
    private void setupNetwork() {

        ParseQuery<ParseObject> query_players = ParseQuery.getQuery("NFLPlayers");
        try {
            query_players.whereEqualTo("FirstName", "TYPE THE STRING TO COMPARE"); // change to where matches
        } catch (NullPointerException e) {
            e.printStackTrace(); // change this to meaning ful message

        }

        query_players.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> playerList, com.parse.ParseException e)
            {
                if (e == null)
                {
                    Log.d("Players","Retrieved " + playerList.size() + " players with the James as firstName");
                    for(int i=0;i<playerList.size();i++)
                    {
                        String shortName = playerList.get(i).getString("ShortName");
                        int  playerID = playerList.get(i).getInt("PlayerID");
                        Log.d("Players","Short Name of the player  : "+shortName+" with id : "+playerID+"\n") ;
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }

        });

    }
}
