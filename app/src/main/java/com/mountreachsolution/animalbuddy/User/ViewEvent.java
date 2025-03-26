package com.mountreachsolution.animalbuddy.User;

import android.app.ProgressDialog;
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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.User.Adpter.AdpterEvent;
import com.mountreachsolution.animalbuddy.User.pojo.POJOEvent;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ViewEvent extends AppCompatActivity {
    RecyclerView rvList;
    TextView tvNoRequest;

    List<POJOEvent> pojoEventList;
AdpterEvent adpterEvent;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_event);
        getWindow().setNavigationBarColor(ContextCompat.getColor(ViewEvent.this,R.color.white));
        getWindow().setStatusBarColor(ContextCompat.getColor(ViewEvent.this,R.color.green));

        rvList = findViewById(R.id.rvLsit);
        tvNoRequest = findViewById(R.id.tvNoRequest);


        rvList.setLayoutManager(new LinearLayoutManager(this));
        pojoEventList = new ArrayList<>();
        progressDialog= new ProgressDialog(this);
        progressDialog.show();


        loadEvents();

    }

    private void loadEvents() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.post(urls.getEvent,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray = response.getJSONArray("getEvent");
                    if (jsonArray.length()==0){
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id =jsonObject.getString("id");
                        String name =jsonObject.getString("name");
                        String loc =jsonObject.getString("loc");
                        String date =jsonObject.getString("date");
                        String stime =jsonObject.getString("Stime");
                        String etime =jsonObject.getString("Etime");
                        String dis =jsonObject.getString("dis");
                        String entry =jsonObject.getString("entry");
                        String image =jsonObject.getString("image");
                        pojoEventList.add(new POJOEvent(id,name,loc,date,stime,etime,dis,entry,image));
                    }
                    adpterEvent = new AdpterEvent(pojoEventList,ViewEvent.this);
                    progressDialog.dismiss();
                    rvList.setAdapter(adpterEvent);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}