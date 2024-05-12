package com.shokii.kedwi;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shokii.kedwi.databinding.FragmentGameInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class GameInfo extends Fragment {
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private DatabaseReference gameRef;
    private FragmentGameInfoBinding binding;
    Bundle bundle = new Bundle();

    private String[] _monthMap = { "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь" };

    public GameInfo() {
        super(R.layout.fragment_game_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameInfoBinding.inflate(getLayoutInflater());

        bundle = getArguments();
        String GameName = bundle.getString("NAME");
        String Status = bundle.getString("STATUS");

        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference().child("games").child(Status).child(GameName);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("games_covers");

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                storageRef.child(snapshot.child("cover").getValue().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getContext())
                                .load(uri)
                                .optionalCenterCrop()
                                .into(binding.gameLogo);
                    }
                });

                if (!snapshot.child("date").getValue().toString().equals("")) {
                    String[] date = snapshot.child("date").getValue().toString().split("/");
//                    if (date.length != 1)
                    binding.dateDesc.setText(date[0] + " " + _monthMap[Integer.parseInt(date[1]) - 1] + " " + date[2]);
                }
                else
                    binding.dateDesc.setText("Дата будет объявлена позже");


                ArrayList<String> temp = new ArrayList<>();
                for (DataSnapshot developers : snapshot.child("developers").getChildren())
                    temp.add(developers.getKey());


                for (int i = 0; i < temp.size() - 1; i++)
                    binding.developerDesc.append(temp.get(i) + "\n");
                binding.developerDesc.append(temp.get(temp.size() - 1));


                binding.publisherDesc.setText(snapshot.child("publishers").getValue().toString());

                switch (snapshot.child("pegi").getValue().toString()) {
                    case "3":
                        binding.pegiDesc.setImageResource(R.drawable.pegi3);
                        break;
                    case "7":
                        binding.pegiDesc.setImageResource(R.drawable.pegi7);
                        break;
                    case "12":
                        binding.pegiDesc.setImageResource(R.drawable.pegi12);
                        break;
                    case "16":
                        binding.pegiDesc.setImageResource(R.drawable.pegi16);
                        break;
                    case "18":
                        binding.pegiDesc.setImageResource(R.drawable.pegi18);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.backBtn.setOnClickListener(this::back);


        binding.gameName.setText(GameName);


        return binding.getRoot();
    }

    private void back(View view) {
        if (getFragmentManager() != null)
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new HomePage()).commit();
    }


}