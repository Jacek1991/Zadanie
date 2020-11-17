package com.example.zadanie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
public class MainActivity extends AppCompatActivity {


    ImageView logo;
    RequestQueue requestQueue;
    String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    String url;
    int tempMin;
    int tempMax;
    int tempActual;
    String weather;
    double windSpeed;
    int pressure;
    String city;

    EditText cityInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityInput = findViewById(R.id.editTextCity);



    }

    private void startAnim(){
        Animation alpha = new AlphaAnimation(0f,1f);
        alpha.setDuration(5000);

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logo.startAnimation(alpha);
    }
    private void getForecast(final String city){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        url = baseUrl + city + "&APPID=b3c301081f553255bf8e728e3a26613d&lang=pl";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tempActual = (int) Math.round(response.getJSONObject("main").getDouble("temp") - 273.15);
                    tempMax = (int) Math.ceil( response.getJSONObject("main").getDouble("temp_max") - 273.15);
                    tempMin = (int) Math.floor(response.getJSONObject("main").getDouble("temp_min") - 273.15);
                    weather = response.getJSONArray("weather").getJSONObject(0).get("description").toString();
                    pressure = (int)response.getJSONObject("main").get("pressure");
                    windSpeed =  response.getJSONObject("wind").getDouble("speed");
                    Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("city", city);
                    bundle.putString("weather", weather);
                    bundle.putInt("tempMin", tempMin);
                    bundle.putInt("tempMax", tempMax);
                    bundle.putInt("tempActual", tempActual);
                    bundle.putInt("pressure", pressure);
                    bundle.putDouble("windSpeed", windSpeed);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Wprowadź poprawnie nazwę miasta", Toast.LENGTH_LONG).show();;
            }
        });

        queue.add(objectRequest);
    }

    public void onClick(View view){
        city = cityInput.getText().toString();
        getForecast(city);

    }

}