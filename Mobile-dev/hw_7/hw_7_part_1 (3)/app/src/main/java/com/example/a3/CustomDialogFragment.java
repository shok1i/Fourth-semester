package com.example.a3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    // POINT 3.1
    // Проверьте работу приложения, по нажатию на кнопку вызвав диалоговое окно. Убедитесь,
    // что при повороте экрана устройства открытое диалоговое окно восстанавливается.
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        return builder.setTitle("Диалоговое окно").setMessage("Для закрытия нажмите OK").create();
//    }


    // POINT 3.2
    // Кастомизируем диалоговое окно, для этого отредактируйте код метода onCreateDialog() в
    // классе фрагмента
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Диалоговое окно")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog)
                .setPositiveButton("OK", null)
                .setNegativeButton("Отмена", null)
                .create();
    }
}
