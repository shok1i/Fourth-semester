package com.shokii.kedwi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shokii.kedwi.databinding.FragmentHomePageBlankBinding;

import java.util.ArrayList;
import java.util.Objects;


// TODO:



public class HomePage_Blank extends Fragment {
    static int game_status;

    FragmentHomePageBlankBinding homePageBlankBinding;

    //    Firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

    // Games lists
    ArrayList<GameItem>
            OUT = new ArrayList<>(), ANNOUNCEMENT = new ArrayList<>();


    public static Fragment newInstance(int position) {
        game_status = position;
        return new HomePage_Blank();
    }

    public HomePage_Blank(){
            super(R.layout.fragment_home_page_blank);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homePageBlankBinding = FragmentHomePageBlankBinding.inflate(getLayoutInflater());

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title, src, userGameStatus = "not played";

                Iterable<DataSnapshot> GAME_OUT = snapshot.child("games").child("out").getChildren();
                for (DataSnapshot games : GAME_OUT) {
                    title = games.getKey();
                    src = games.child("cover").getValue().toString();

                    Iterable<DataSnapshot> USER = snapshot.child("users").child(mAuth.getUid()).child("game statistic").getChildren();
                    for (DataSnapshot userGames : USER)
                        for (DataSnapshot item : userGames.getChildren())
                            if (Objects.equals(title, item.getKey()))
                                userGameStatus = userGames.getKey();

                    GameItem gameItem = new GameItem(title, src, userGameStatus);
                    OUT.add(gameItem);
                    userGameStatus = "not played";
                }

                Iterable<DataSnapshot> GAME_ANNOUNCEMENT = snapshot.child("games").child("announcement").getChildren();
                for (DataSnapshot games : GAME_ANNOUNCEMENT) {
                    title = games.getKey();
                    src = games.child("cover").getValue().toString();

                    Iterable<DataSnapshot> USER = snapshot.child("users").child(mAuth.getUid()).child("game statistic").getChildren();
                    for (DataSnapshot userGames : USER)
                        for (DataSnapshot item : userGames.getChildren())
                            if (Objects.equals(title, item.getKey()))
                                userGameStatus = userGames.getKey();

                    GameItem gameItem = new GameItem(title, src, userGameStatus);
                    ANNOUNCEMENT.add(gameItem);
                    userGameStatus = "not played";
                }


                GridViewAdapter adapter = game_status == 0 ? new GridViewAdapter(getContext(), OUT) : new GridViewAdapter(getContext(), ANNOUNCEMENT);
                homePageBlankBinding.gamesGrid.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        homePageBlankBinding.gamesGrid.setOnItemClickListener(this::itemClick);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return homePageBlankBinding.getRoot();
    }

    private void itemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GameItem item = game_status == 0 ? OUT.get(i) : ANNOUNCEMENT.get(i);
        String gameStatus = game_status == 0 ? "out" : "announcement";

        Bundle bundle = new Bundle();
        bundle.putString("NAME", item.gameTitle);
        bundle.putString("IMG_SRC", item.imgSrc);
        bundle.putString("GAME_STATUS", gameStatus);
        bundle.putString("USER_GAME_STATUS", item.gameStatus);

        GameInfo gameInfo = new GameInfo();
        gameInfo.setArguments(bundle);

        if (getFragmentManager() != null)
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, gameInfo).commit();
    }
}