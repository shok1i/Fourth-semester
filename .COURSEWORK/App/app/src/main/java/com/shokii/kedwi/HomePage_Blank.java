package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shokii.kedwi.databinding.FragmentHomePageBlankBinding;

import java.util.ArrayList;


// TODO:



public class HomePage_Blank extends Fragment {
    private static int type;
    private ArrayList<GameItem> gameItems = new ArrayList<>();
    private FragmentHomePageBlankBinding _binding;
    private GridViewAdapter adapter;

    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private DatabaseReference gameRef;
    GameInfo frag = new GameInfo();
    String temp;


    public static Fragment newInstance(int position) {
        type = position;
        return new HomePage_Blank();
    }

    public interface SimpleCallback<T> {
        void callback(T data);
    }

    public HomePage_Blank() {
        super(R.layout.fragment_home_page_blank);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentHomePageBlankBinding.inflate(getLayoutInflater());

        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference().child("games");

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("games_covers");

        if (type == 0) {
            gameRef = gameRef.child("out");
            temp = "out";
        }
        else {
            gameRef = gameRef.child("announcement");
            temp = "announcement";
        }

        _binding.Announcements.setOnItemClickListener(this::itemClick);

        returnData(new SimpleCallback<ArrayList<GameItem>>() {
            @Override
            public void callback(ArrayList<GameItem> data) {
                adapter = new GridViewAdapter(getContext(), data);
                _binding.Announcements.setAdapter(adapter);
            }
        });

        return _binding.getRoot();
    }

    private void itemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString("NAME", gameItems.get(i).gameTitle.toString());
        bundle.putString("STATUS", temp);

        frag.setArguments(bundle);

        if (getFragmentManager() != null)
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, frag).commit();
    }

    private void returnData (SimpleCallback<ArrayList<GameItem>> arrayCallBack) {
        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    GameItem gameItem = new GameItem(
                            snap.getKey(),
                            snap.child("cover").getValue().toString()
                    );
                    gameItems.add(gameItem);
                }
                arrayCallBack.callback(gameItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {  }
        });
    }

}