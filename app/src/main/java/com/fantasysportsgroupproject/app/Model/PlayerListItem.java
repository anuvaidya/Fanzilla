package com.fantasysportsgroupproject.app.Model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anu on 7/20/14.
 */


// I think you can delete this later - mon July 21st - Anu
public class PlayerListItem {

    private ImageView imvPlayer;
    private TextView tvPlayerFullName;
    private TextView tvPlayerPosition;

    public ImageView getImvPlayer() {
        return imvPlayer;
    }

    public void setImvPlayer(ImageView imvPlayer) {
        this.imvPlayer = imvPlayer;
    }

    public TextView getTvPlayerFullName() {
        return tvPlayerFullName;
    }

    public void setTvPlayerFullName(TextView tvPlayerFullName) {
        this.tvPlayerFullName = tvPlayerFullName;
    }

    public TextView getTvPlayerPosition() {
        return tvPlayerPosition;
    }

    public void setTvPlayerPosition(TextView tvPlayerPosition) {
        this.tvPlayerPosition = tvPlayerPosition;
    }


}
