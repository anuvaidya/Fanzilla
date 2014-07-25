package com.fantasysportsgroupproject.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.fantasysportsgroupproject.app.R;

/**
 * Created by Anu on 7/17/14.
 */
public class TeamFragment extends Fragment {

    private View view;
    private Spinner spTeamName;
    private Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        view = inflater.inflate(R.layout.fragment_team, container, false);
        // Setup handles to view objects here
        // set up spinner
        setup();

        return view;
    }

    private void setup() {
        spTeamName = (Spinner) view.findViewById(R.id.spTeamName);
        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
    }

}