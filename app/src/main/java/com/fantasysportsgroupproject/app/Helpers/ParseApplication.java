package com.fantasysportsgroupproject.app.Helpers;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Anu on 7/19/14.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // set up for player
        Parse.initialize(this, "N62Sz65NLKHLkU9QI4izgxExxs5uDCjsczQTAAd4", "qLXLUGuVRg2bSCWcNAB754xhTeK6JDMaQd7EHORH");

    }



}
