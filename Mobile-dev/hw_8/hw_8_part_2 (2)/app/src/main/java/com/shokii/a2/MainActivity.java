package com.shokii.a2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.shokii.a2.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String WEATHER_API_URL = "https://worldweather.wmo.int/ru/json/206_ru.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new FetchWeatherTask().execute();
    }

    private class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new URL(WEATHER_API_URL).openStream()))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
//                Log.d("my", "Content:\n" + content);
                return content.toString();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("my", "Exception:\n" + e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                try {
                    JSONObject weatherData = (JSONObject) new JSONObject(json).get("city");
                    JSONObject forecastData = (JSONObject) weatherData.get("forecast");
                    Log.d("my", "JSONObject:\n" + weatherData.toString(4));

//                    Log.d("my", "cityName:\n" + weatherData.get("cityName"));
                    String cityName = weatherData.get("cityName").toString();
//                    Log.d("my", "forecast\n" + forecastData.getJSONArray("forecastDay").toString(4));
                    JSONArray forecastDays = forecastData.getJSONArray("forecastDay");
                    ArrayList<String> temperature = new ArrayList<>();
                    for (int i = 0; i < forecastDays.length(); i++) {
                        JSONObject temp = (JSONObject) forecastDays.get(i);
//                        Log.d("my", "forecastDate: " + temp.get("forecastDate"));
                        String tempDate = temp.get("forecastDate").toString();
                        String temp_weather = temp.get("weather").toString();
                        String temp_minTemp = temp.get("minTemp").toString();
                        String temp_maxTemp = temp.get("maxTemp").toString();
                        String tempString = "Дата: " + tempDate +
                                "\nПогода: " + temp_weather +
                                "\nМинимальная температура: " + temp_minTemp + "°C" +
                                "\nМаксимальная температура: " + temp_maxTemp + "°C";
                        temperature.add(tempString);
                    }
//                    Log.d("my", "temperature:\n " + temperature);
                    binding.town.setText(cityName);
                    temperature.forEach((elem) -> binding.forecast.append(elem + "\n\n"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("my", "Exception:\n" + e);
                }
            }
        }
    }
}