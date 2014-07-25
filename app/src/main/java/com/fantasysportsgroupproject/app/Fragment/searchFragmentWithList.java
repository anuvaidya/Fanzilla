package com.fantasysportsgroupproject.app.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantasysportsgroupproject.app.R;

/**
 * Created by Anu on 7/13/14.
 */

// This can be used later if neccessary to dynamically load
// delete this and use favorites
public class searchFragmentWithList extends Fragment {


    //to inflate fragment, every fragment should contain
    // onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.player_list, container, false);
        // Setup handles to view objects here

        // etFoo = (EditText) v.findViewById(R.id.etFoo);
        return view;
    }
}
