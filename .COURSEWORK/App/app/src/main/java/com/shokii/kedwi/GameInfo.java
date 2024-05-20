package com.shokii.kedwi;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shokii.kedwi.databinding.FragmentGameInfoBinding;

import java.util.ArrayList;

public class GameInfo extends Fragment {
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private DatabaseReference gameRef;
    private FragmentGameInfoBinding binding;
    Bundle bundle = new Bundle();

    FirebaseAuth mAuth;
    DatabaseReference userGameRef;

    private int[] backgroundResources = {
            R.drawable.status_not_played,
            R.drawable.status_passing,
            R.drawable.status_planned,
            R.drawable.status_pass,
            R.drawable.status_postponed,
            R.drawable.status_abandoned
    };

    String[] options = {"Не играл", "Прохожу", "В планах", "Пройдено", "Отложено", "Брошено"};
    String[] temp = {"not played", "passing", "planned", "pass", "postponed", "abandoned"};

    private String[] _monthMap = { "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь" };

    public GameInfo() {
        super(R.layout.fragment_game_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameInfoBinding.inflate(getLayoutInflater());

        bundle = getArguments();

        mAuth = FirebaseAuth.getInstance();
        userGameRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getUid()).child("game statistic");


        String gameName = bundle.getString("NAME");
        String imgSrc = bundle.getString("IMG_SRC");
        String gameStatus = bundle.getString("GAME_STATUS");
        String userGameStatus = bundle.getString("USER_GAME_STATUS");

        Button btn = binding.changeGameStatus;
        int i;
        for (i = 0; i < options.length - 1; i++) {
            if (userGameStatus.equals(temp[i])) break;
        }
        btn.setBackground(getResources().getDrawable(backgroundResources[i]));
        btn.setTextColor(getResources().getColor(R.color.grey));
        if (i == 0) btn.setTextColor(getResources().getColor(R.color.black));
        btn.setText(options[i]);

        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference().child("games").child(gameStatus).child(gameName);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("games_covers");

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                storageRef.child(imgSrc).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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

                int pegiValue = Integer.parseInt(snapshot.child("pegi").getValue().toString());
                int resourceId = getResources().getIdentifier("pegi" + pegiValue, "drawable", "com.shokii.kedwi");
                binding.pegiDesc.setImageResource(resourceId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.backBtn.setOnClickListener(this::back);

        binding.changeGameStatus.setOnClickListener(this::changeGameStatus);


        binding.gameName.setText(gameName);


        return binding.getRoot();
    }

    private void changeGameStatus(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите один вариант");

        Button btn = binding.changeGameStatus;
        String search = btn.getText().toString();
        int i;
        for (i = 0; i < options.length; i++) {
            if (search.equals(options[i])) break;
        }
        String currentStatus = temp[i];
        String gameName = binding.gameName.getText().toString();

        builder.setItems(options, (dialog, which) -> {
            btn.setBackground(getResources().getDrawable(backgroundResources[which]));
            btn.setTextColor(getResources().getColor(R.color.grey));
            if (which == 0) btn.setTextColor(getResources().getColor(R.color.black));
            btn.setText(options[which]);

            changeUserGameStatus(gameName, currentStatus, temp[which]);
        });

        builder.show();
    }
    private void changeUserGameStatus(String gameName, String prevStatus, String newStatus) {
        userGameRef.child(prevStatus).child(gameName).removeValue();
        userGameRef.child(newStatus).child(gameName).setValue("flag");
    }

    private void back(View view) {
        if (bundle.getBoolean("PAGE"))
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new BookmarksPage()).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new HomePage()).commit();
    }


}