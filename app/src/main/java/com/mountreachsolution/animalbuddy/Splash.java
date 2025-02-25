package com.mountreachsolution.animalbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mountreachsolution.animalbuddy.Rescuser.RescueHomepage;
import com.mountreachsolution.animalbuddy.User.UserHomepage;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        new Handler().postDelayed(this::checkLoginStatus, 2000); // 2-second splash delay
    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            String userType = sharedPreferences.getString("userType", null);

            if (userType != null) {
                if (userType.equalsIgnoreCase("Rescuer")) {
                    // Navigate to Saviour Homepage
                    startActivity(new Intent(this, RescueHomepage.class));
                } else if (userType.equalsIgnoreCase("User")) {
                    // Navigate to Needy Homepage
                    startActivity(new Intent(this, UserHomepage.class));
                }
            }
            finish();
        } else {
            // Redirect to Login Screen
            startActivity(new Intent(this, Loginactivity.class));
            finish();
        }
    }
}