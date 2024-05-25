package com.shokii.kedwi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shokii.kedwi.databinding.ActivityMainBinding;
import com.theartofdev.edmodo.cropper.CropImage;

public class MainActivity extends AppCompatActivity /* implements BottomNavigationView.OnNavigationItemSelectedListener */ {
    private ActivityMainBinding _binding;

    FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());


        mAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("user_image");

        _binding.bottomNav.setSelectedItemId(R.id.menu_home);
        _binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int curPage = _binding.bottomNav.getSelectedItemId();

                if (item.getItemId() == R.id.menu_home && curPage != R.id.menu_home) {
                    // menu_home
                    loadFragment(new HomePage());
                    return true;
                }
                if (item.getItemId() == R.id.menu_calendar && curPage != R.id.menu_calendar) {
                    // menu_calendar
                    loadFragment(new CalendarPage());
                    return true;
                }if (item.getItemId() == R.id.menu_bookmarks && curPage != R.id.menu_bookmarks) {
                    // menu_bookmarks
                    loadFragment(new BookmarksPage());
                    return true;
                }if (item.getItemId() == R.id.menu_user && curPage != R.id.menu_user) {
                    // menu_user
                    loadFragment(new UserPage());
                    return true;
                }
                return false;
            }
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new HomePage()).commit();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && data != null) {
            Uri uri = CropImage.getActivityResult(data).getUri();
            storageRef.child(mAuth.getUid()).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Toast.makeText(MainActivity.this, "Фото обновлено", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}