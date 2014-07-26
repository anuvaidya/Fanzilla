package com.fantasysportsgroupproject.app.Helpers;

import com.fantasysportsgroupproject.app.Fragment.Player;

import java.util.ArrayList;

/**
 * Created by Anu on 7/25/14.
 */
public class MySingleton {
    private static MySingleton instance;

    public Player newPlayer;
    public ArrayList<Player> arrayOfPlayers;

    public static MySingleton getInstance()
    {
        return instance;

    }

    public static void initInstance()
    {
        if (instance == null) {
            // create the instance
            instance = new MySingleton();
            instance.newPlayer = new Player();
            instance.arrayOfPlayers = new ArrayList<Player>(0);
        }

    }
    private MySingleton()
    {
        //constructor hidden because this is singleton
    }
}
