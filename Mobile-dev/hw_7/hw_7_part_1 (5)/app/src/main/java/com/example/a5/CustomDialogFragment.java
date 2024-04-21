package com.example.a5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    // 7 lines
    private Removable _removable;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _removable = (Removable) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        // 2 line
        final String phone = getArguments().getString("phone");
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        return builder
                .setTitle("Диалоговое окно")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Удалить " + phone + "?")
                /* 6 lines */
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _removable.remove(phone);
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
