package com.example.zadanie;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ForecastActivity extends AppCompatActivity {
    int tempMin;
    int tempMax;
    int tempActual;
    String weather;
    double windSpeed;
    int pressure;
    String city;
    TextView weatherText;
    TextView tempActualText;
    TextView tempMinText;
    TextView tempMaxText;
    TextView pressureText;
    TextView windSpeedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        tempMin = bundle.getInt("tempMin");
        tempMax = bundle.getInt("tempMax");
        tempActual = bundle.getInt("tempActual");
        weather = bundle.getString("weather");
        windSpeed = bundle.getDouble("windSpeed");
        pressure = bundle.getInt("pressure");
        city = bundle.getString("city");
        weatherText = findViewById(R.id.weatherText);
        tempActualText = findViewById(R.id.tempActualText);
        tempMinText = findViewById(R.id.tempMinText);
        tempMaxText = findViewById(R.id.tempMaxText);
        pressureText = findViewById(R.id.pressureText);
        windSpeedText = findViewById(R.id.windSpeedText);

        weatherText.setText("Pogoda w mieście " + city + ": " + weather);
        tempActualText.setText(tempActual + " " + "\u00B0" + "C");
        tempMinText.setText("Min: " + tempMin  + " " +  "\u00B0" + "C");
        tempMaxText.setText("Max: " + tempMax  + " " + "\u00B0" + "C" );
        pressureText.setText("Ciśnienie: " + pressure + " hPa");
        windSpeedText.setText("Prędkość wiatru: " + windSpeed + " km/h");


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForecastActivity.this, MainActivity.class);
        startActivity(intent);
    }
}