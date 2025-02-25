package com.mountreachsolution.animalbuddy.User;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Bitprecussion extends AppCompatActivity {
    Spinner animalSpinner;
    TextView precautionText;
    ArrayList<String> animalList = new ArrayList<>();
    HashMap<String, String> precautionMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bitprecussion);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));
        animalSpinner = findViewById(R.id.animalSpinner);
        precautionText = findViewById(R.id.precautionText);

        fetchAnimalData();

    }
    private void fetchAnimalData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urls.animalbite, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray animalArray = response.getJSONArray("animalbite");
                            for (int i = 0; i < animalArray.length(); i++) {
                                JSONObject obj = animalArray.getJSONObject(i);
                                String animalName = obj.getString("animal");
                                String precaution = obj.getString("precusion");

                                animalList.add(animalName);
                                precautionMap.put(animalName, precaution);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Bitprecussion.this, android.R.layout.simple_spinner_item, animalList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            animalSpinner.setAdapter(adapter);

                            animalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedAnimal = animalList.get(position);
                                    precautionText.setText(precautionMap.get(selectedAnimal));
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    precautionText.setText("");
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Bitprecussion.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }






}