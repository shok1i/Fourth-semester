package com.shokii.kedwi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<GameItem> {
    public GridViewAdapter(@NonNull Context context, ArrayList<GameItem> gameItems) {
        super(context, 0, gameItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null)
            listView = LayoutInflater.from(getContext()).inflate(R.layout.game_item, parent, false);

        GameItem gameItem = getItem(position);

        ImageView gameLogo = (ImageView) listView.findViewById(R.id.gameLogo);
        TextView gameText = (TextView) listView.findViewById(R.id.gameText);
        TextView gameStatus = (TextView) listView.findViewById(R.id.gameStatus);

        gameLogo.setImageResource(R.drawable.test);
        gameText.setText(gameItem.gameTitle);
        gameStatus.setText(gameItem.gameStatus);

        return listView;
    }
}
