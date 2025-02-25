package com.mountreachsolution.animalbuddy.Rescuser;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.ADAPTER.AdpterFeedback;
import com.mountreachsolution.animalbuddy.Rescuser.ADAPTER.AdpterHistory;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOFeedback;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOGetHistory;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Histrory extends AppCompatActivity {
    RecyclerView rvList;
    TextView tvNoRequest;
    List<POJOGetHistory>pojoGetHistories;
    AdpterHistory adpterHistory;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_histrory);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        SharedPreferences sharedPreferences =getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Guest");
        rvList = findViewById(R.id.rvLsit);
        tvNoRequest = findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(Histrory.this));
        pojoGetHistories=new ArrayList<>();
        getData();

    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(Histrory.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urls.getHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("getHistory");
                    if (jsonArray.length() == 0) {
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String Rusername = jsonObject1.getString("Rescuerusername");
                        String id = jsonObject1.getString("id");
                        String location = jsonObject1.getString("location");
                        String detail = jsonObject1.getString("details");
                        String Uusername = jsonObject1.getString("Userusername");
                        String name = jsonObject1.getString("name");
                        String image = jsonObject1.getString("image");
                        String status = jsonObject1.getString("status");
                        pojoGetHistories.add(new POJOGetHistory(id, Rusername, location, detail, Uusername, name, image, status));
                    }
                    adpterHistory = new AdpterHistory(pojoGetHistories, Histrory.this);
                    rvList.setAdapter(adpterHistory);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error here
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Rescuerusername", username); // Passing the username to the server
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}