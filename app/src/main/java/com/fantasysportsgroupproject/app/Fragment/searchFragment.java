package com.fantasysportsgroupproject.app.Fragment;

// this import is needed to support backward compatibility

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantasysportsgroupproject.app.R;

/**
 * Created by Anu on 7/13/14.
 */
public class searchFragment extends Fragment {

    //to inflate fragment, every fragment should contain
    // onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.activity_search, container, false);
        // Setup handles to view objects here
        // etFoo = (EditText) v.findViewById(R.id.etFoo);
        return view;
    }

}
