package com.mountreachsolution.animalbuddy.Rescuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.ADAPTER.AdpterAllRequest;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOAllRequest;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ViewComplain extends Fragment {
    RecyclerView rvList;
    TextView tvNoRequest;
    List<POJOAllRequest>pojoAllRequests;
    AdpterAllRequest adpterAllRequest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_complain, container, false);
        rvList = view.findViewById(R.id.rvLsit);
        tvNoRequest = view.findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        pojoAllRequests=new ArrayList<>();
        getData();
        return view;
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urls.getAllRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("getRequest");
                    if (jsonArray.length()==0){
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        String username=jsonObject1.getString("username");
                        String location=jsonObject1.getString("location");
                        String details=jsonObject1.getString("details");
                        String image=jsonObject1.getString("image");
                        pojoAllRequests.add(new POJOAllRequest(id,username,location,details,image));
                    }
                    adpterAllRequest = new AdpterAllRequest(pojoAllRequests,getActivity());
                    rvList.setAdapter(adpterAllRequest);
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
