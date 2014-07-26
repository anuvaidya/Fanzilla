package com.fantasysportsgroupproject.app.Helpers;

import android.content.Context;
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
    private ImageView imvPlayer;
    private TextView tvPlayerFullName;
    private TextView tvPlayerPosition;

     public PlayerSearchListArrayAdapter(Context context,  List<Player> playerList) {
         super(context, 0 , playerList);
     }


    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // get the data item for position
        Player player = getItem(position);

        // find if the view exists. what that means if we reached the max limit
        // of items to view on the screen, then Android starts to recycle the
        // previous viewable list items.
        // If the view does not exist, we have to create one through the inflater

        View v;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.player_list_item,parent,false); // check if to attach to root
        }
        else {
            v = convertView;
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
        //getTweetUser().getProfileImageUrl();
        Picasso.with(getContext()).load(imageUri).into(imvPlayer);
        tvPlayerFullName.setText(player.getPlayerFirstName() + " " + player.getPlayerLastName());
        tvPlayerPosition.setText(player.getFantasyPosition());


        return v;

    }

}
