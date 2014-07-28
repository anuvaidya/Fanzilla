package com.fantasysportsgroupproject.app.Helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fantasysportsgroupproject.app.Fragment.Player;
import com.fantasysportsgroupproject.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//import com.parse.R;

/**
 * Created by Anu on 7/21/14.
 */

// Adapter for the list: inflates player_list_item
public class PlayerSearchListArrayAdapter extends ArrayAdapter<Player>{

    public static final String TAG = "PLAYER_SEARCH_LIST_ADAPTER";
    private ImageView imvPlayer;
    private TextView tvPlayerFullName;
    private TextView tvPlayerPosition;

    List<Player> playerList;
    LayoutInflater mInflater;

     public PlayerSearchListArrayAdapter(Context context,List<Player> playerList) {
         //super(context, 0 , playerList);
         super(context,0,playerList);
         mInflater = LayoutInflater.from(context);
         this.playerList = playerList;
     }


    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        Log.d(TAG,"inside adapter");

        Log.d(TAG,"position = " + position);
        Log.d(TAG,"activity = " + getContext());


        // get the data item for position
        Player player = getItem(position);
        Log.d(TAG,"player list size from within the adapter: = " + playerList.size());
        Log.d(TAG,"player's last name = " + player.getPlayerLastName());
        Log.d(TAG,"player's id: 8th in  the list " + playerList.get(8).getPlayerID());

        // find if the view exists. what that means if we reached the max limit
        // of items to view on the screen, then Android starts to recycle the
        // previous viewable list items.
        // If the view does not exist, we have to create one through the inflater

        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
           // v = inflater.inflate(R.layout.player_list_item,parent,false);
           // check if to attach to root
            v = inflater.inflate(R.layout.player_list_item,parent,false);

        }


        // find the ids in the tweet_item.xml
        ImageView imvPlayer = (ImageView)v.findViewById(R.id.imvPlayer);
        TextView tvPlayerFullName = (TextView)v.findViewById(R.id.tvPlayerFullName);
        TextView tvPlayerPosition = (TextView)v.findViewById(R.id.tvPlayerPosition);

        // using the imageLoader to load the image
        //ImageLoader imgLoader = ImageLoader.getInstance();
        //imgLoader.displayImage(tweet.getTweetUser().getProfileImageUrl(),imvProfileImage);

        // populate the items
        // ImageUri is the url of the image
        String imageUri = player.getPhotoUrl();
        Log.d(TAG,imageUri);
     //   String imageUri = "http://static.nfldata.com/images/player/1944.jpg";
        //getTweetUser().getProfileImageUrl();
        Picasso.with(getContext()).load(imageUri).into(imvPlayer);
       // tvPlayerFullName.setText(player.getPlayerFirstName() + " " + player.getPlayerLastName());
        tvPlayerFullName.setText(player.getPlayerShortName());
        tvPlayerPosition.setText(player.getFantasyPosition());


        return v;

    }



}


