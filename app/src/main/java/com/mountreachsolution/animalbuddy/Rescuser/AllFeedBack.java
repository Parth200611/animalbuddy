package com.mountreachsolution.animalbuddy.Rescuser;

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
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOFeedback;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllFeedBack extends AppCompatActivity {
    RecyclerView rvList;
    TextView tvNoRequest;
    List<POJOFeedback>pojoFeedbacks;
    AdpterFeedback adpterFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_feed_back);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        rvList = findViewById(R.id.rvLsit);
        tvNoRequest = findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(AllFeedBack.this));
        pojoFeedbacks=new ArrayList<>();

        getData();


    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(AllFeedBack.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urls.getFeddback, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray =jsonObject.getJSONArray("getFeedback");
                    if (jsonArray.length()==0){
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                        String username=jsonObject1.getString("username");
                        String id=jsonObject1.getString("id");
                        String feedback=jsonObject1.getString("feedback");
                        String date=jsonObject1.getString("date");
                        String time=jsonObject1.getString("time");
                        pojoFeedbacks.add(new POJOFeedback(id,username,feedback,date,time));
                    }
                    adpterFeedback =new AdpterFeedback(pojoFeedbacks,AllFeedBack.this);
                    rvList.setAdapter(adpterFeedback);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}