package com.mountreachsolution.animalbuddy.User;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class Feedback extends AppCompatActivity {
    EditText feedbackInput;
    Button btnPost;
    String username;
    String currentDate, currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white));

        // Getting username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Guest");

        // Initialize views
        feedbackInput = findViewById(R.id.feedbackInput);
        btnPost = findViewById(R.id.postFeedbackButton);

        // Post button click listener
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = feedbackInput.getText().toString().trim();
                currentDate = getCurrentDate();
                currentTime = getCurrentTime();

                // Validation
                if (feedback.isEmpty()) {
                    Toast.makeText(Feedback.this, "Enter the feedback", Toast.LENGTH_SHORT).show();
                } else {
                    PostFeedback(feedback, username, currentDate, currentTime);
                }
            }
        });
    }

    private void PostFeedback(String feedback, String username, String currentDate, String currentTime) {
        AsyncHttpClient client =new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("feedback",feedback);
        params.put("date",currentDate);
        params.put("time",currentTime);
        client.post(urls.AddFeedBack,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(Feedback.this, "Thank You For Your FeedBack!", Toast.LENGTH_SHORT).show();
                        feedbackInput.setText("");
                    }else{
                        Toast.makeText(Feedback.this, "Fail to Add Feedback", Toast.LENGTH_SHORT).show();
                    }
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


    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date currentDate = Calendar.getInstance().getTime();
        return dateFormat.format(currentDate);
    }

   
    public static String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date currentTime = Calendar.getInstance().getTime();
        return timeFormat.format(currentTime);
    }

  
  
}
