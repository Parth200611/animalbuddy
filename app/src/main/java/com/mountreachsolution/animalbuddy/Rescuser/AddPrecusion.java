package com.mountreachsolution.animalbuddy.Rescuser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.urls;

import java.util.HashMap;
import java.util.Map;

public class AddPrecusion extends AppCompatActivity {

    EditText animalName, precautionText;
    Button addPrecautionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_precusion);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));

        animalName = findViewById(R.id.animalName);
        precautionText = findViewById(R.id.precautionText);
        addPrecautionBtn = findViewById(R.id.addPrecautionBtn);

        addPrecautionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPrecautionToServer();
            }
        });
    }
    private void addPrecautionToServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urls.addanimalbite,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddPrecusion.this, "Precaution Added Successfully", Toast.LENGTH_SHORT).show();
                        animalName.setText("");
                        precautionText.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddPrecusion.this, "Failed to add precaution", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("animal", animalName.getText().toString().trim());
                params.put("precusion", precautionText.getText().toString().trim());
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}