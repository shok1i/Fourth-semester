package com.example.kedwi;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalUserDataJSON {
    private static final String FILE_NAME = "LocalUser.json";

    static boolean ToJSON(Context context, LocalUserData userData) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(userData);
        try (FileOutputStream fileOutputStream =
                     context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    static LocalUserData FromJSON (Context context) {
        try (FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
                InputStreamReader streamReader = new InputStreamReader(fileInputStream)) {
            Gson gson = new Gson();
            return gson.fromJson(streamReader, LocalUserData.class);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }
}
