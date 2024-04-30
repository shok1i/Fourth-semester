package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        return builder
                .setTitle("Диалоговое окно")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Dialog", "OnCreate()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Dialog", "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Dialog", "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Dialog", "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Dialog", "onStop()");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Dialog", "OnCreate()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Dialog", "onDetach()");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Dialog", "onAttach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Dialog", "onDestroy()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Dialog", "onDestroyView()");
    }

}
