package com.shokii.kedwi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shokii.kedwi.databinding.FragmentBookmarksPageBlankBinding;

import java.util.ArrayList;


public class BookmarksPageBlank extends Fragment {

    private int GAME_TYPE = 0;
    FragmentBookmarksPageBlankBinding bookmarksPageBlank;

    // Firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

    // Games lists
    ArrayList<ArrayList <GameItem>> list = new ArrayList<>(6);

    public BookmarksPageBlank(int game_type) {
        super(R.layout.fragment_bookmarks_page_blank);
        GAME_TYPE = game_type;
    }

    public static Fragment newInstance(int position) {
        return new BookmarksPageBlank(position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookmarksPageBlank = FragmentBookmarksPageBlankBinding.inflate(getLayoutInflater());
        for (int i = 0; i < 6; i++) {
            list.add(new ArrayList<>());
        }

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int i = 0; i < 6; i++) {
                    list.set(i, new ArrayList<>());
                }

                Iterable<DataSnapshot>
                        USER_GAMES = snapshot.child("users").child(mAuth.getUid()).child("game statistic").getChildren();

                DataSnapshot
                        GAMES = snapshot.child("games").child("out");

                for (DataSnapshot user_status : USER_GAMES) {
                    for (DataSnapshot user_game : user_status.getChildren()) {

                        String imgSrc = GAMES.child(user_game.getKey()).child("cover").getValue().toString();
                        GameItem gameItem = new GameItem(user_game.getKey(), imgSrc, user_status.getKey());

                        switch (user_status.getKey()){
                            case "passing":
                                list.get(1).add(gameItem);
//                                passing_list.add(gameItem);
                                break;
                            case "planned":
                                list.get(2).add(gameItem);
//                                planned_list.add(gameItem);
                                break;
                            case "pass":
                                list.get(3).add(gameItem);
//                                pass_list.add(gameItem);
                                break;
                            case "postponed":
                                list.get(4).add(gameItem);
//                                postponed_list.add(gameItem);
                                break;
                            case "abandoned":
                                list.get(5).add(gameItem);
//                                abandoned_list.add(gameItem);
                                break;
                        }
                    }
                }

                GridViewAdapter adapter = new GridViewAdapter(getContext(), list.get(GAME_TYPE));
                bookmarksPageBlank.gamesGridBookmarks.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bookmarksPageBlank.gamesGridBookmarks.setOnItemClickListener(this::itemClick);
        return bookmarksPageBlank.getRoot();
    }

    private void itemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GameItem item = list.get(GAME_TYPE).get(i);

        Bundle bundle = new Bundle();
        bundle.putString("NAME", item.gameTitle);
        bundle.putString("IMG_SRC", item.imgSrc);
        bundle.putString("GAME_STATUS", "out");
        bundle.putString("USER_GAME_STATUS", item.gameStatus);
        bundle.putBoolean("PAGE", true);

        GameInfo gameInfo = new GameInfo();
        gameInfo.setArguments(bundle);

        if (getFragmentManager() != null)
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, gameInfo).commit();
    }
}