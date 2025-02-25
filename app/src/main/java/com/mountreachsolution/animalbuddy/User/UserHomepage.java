package com.mountreachsolution.animalbuddy.User;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowInsetsController;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mountreachsolution.animalbuddy.R;

public class UserHomepage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_homepage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.green));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false);
            window.getInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        getWindow().setNavigationBarColor(ContextCompat.getColor(UserHomepage.this,R.color.white));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigationView = findViewById(R.id.bottomnevigatiomuserhome);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.UPostCompain);

    }
    Userprofil userprofil = new Userprofil();
    PostComplaint postComplaint = new PostComplaint();
    ComplainStatus complainStatus = new ComplainStatus();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.UPostCompain){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,postComplaint).commit();
        }else if(item.getItemId()==R.id.UStatus){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,complainStatus).commit();
        } else if(item.getItemId()==R.id.UProfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,userprofil).commit();
        }
        return true;
    }
}