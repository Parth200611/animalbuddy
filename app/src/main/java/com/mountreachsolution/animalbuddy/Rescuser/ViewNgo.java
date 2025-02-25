package com.mountreachsolution.animalbuddy.Rescuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.ADAPTER.AdpterNgo;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJONgo;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ViewNgo extends Fragment {
    RecyclerView rvList;
    TextView tvNoRequest;
    List<POJONgo>pojoNgos;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_ngo, container, false);
        rvList = view.findViewById(R.id.rvLsit);
        tvNoRequest = view.findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        pojoNgos=new ArrayList<>();
        getData();
        return view;
    }

    private void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.post(urls.getNgo,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray =response.getJSONArray("getNgo");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String name=jsonObject.getString("name");
                        String mobile=jsonObject.getString("mobile");
                        String address=jsonObject.getString("address");
                        pojoNgos.add(new POJONgo(id,name,mobile,address));

                    }
                    AdpterNgo adpterNgo =new AdpterNgo(pojoNgos,getActivity());
                    rvList.setAdapter(adpterNgo);
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