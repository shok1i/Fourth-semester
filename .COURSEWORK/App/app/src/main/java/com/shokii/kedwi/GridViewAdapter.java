package com.shokii.kedwi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;

// TODO:
//   Сохранять картинки в кэше чтобы не было постоянной загрузки картинок при прокрутки
//   Пофиксить баг прокрутки (заключается в том что каждый раз мы заново качаем картинку)



public class GridViewAdapter extends ArrayAdapter<GameItem> {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef, gameRef;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    public GridViewAdapter(@NonNull Context context, ArrayList<GameItem> gameItems) {
        super(context, 0, gameItems);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("games_covers");

        View listView = convertView;
        if (listView == null)
            listView = LayoutInflater.from(getContext()).inflate(R.layout.game_item, parent, false);

        GameItem gameItem = getItem(position);

        ImageView gameLogo = (ImageView) listView.findViewById(R.id.gameLogo);
        TextView gameText = (TextView) listView.findViewById(R.id.gameText);
        TextView gameStatus = (TextView) listView.findViewById(R.id.gameStatus);

        storageRef.child(gameItem.imgSrc).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext())
                        .load(uri)
                        .optionalCenterCrop()
                        .into(gameLogo);
            }
        });

        gameText.setText(gameItem.gameTitle.toString());
        gameStatus.setText(" ");

        return listView;
    }
}
