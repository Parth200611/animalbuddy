package com.mountreachsolution.animalbuddy.Rescuser;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.mountreachsolution.animalbuddy.User.Bitprecussion;
import com.mountreachsolution.animalbuddy.User.Feedback;
import com.mountreachsolution.animalbuddy.User.UserHomepage;

public class RescueHomepage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rescue_homepage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.green));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false);
            window.getInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        getWindow().setNavigationBarColor(ContextCompat.getColor(RescueHomepage.this,R.color.white));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigationView = findViewById(R.id.bottomnevigatiomuserhome);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.RViewComplain);

    }
    ViewComplain viewComplain=new ViewComplain();
    ViewNgo viewNgo = new ViewNgo();
    RescuProfil rescuProfil = new RescuProfil();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.RViewComplain){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,viewComplain).commit();
        }else if(item.getItemId()==R.id.RNgo){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,viewNgo).commit();
        } else if(item.getItemId()==R.id.RProfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,rescuProfil).commit();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.rescuemenut,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.RFeedback){
            Intent i = new Intent(RescueHomepage.this, AllFeedBack.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.RHistrory) {
            Intent i = new Intent(RescueHomepage.this, Histrory.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.RPrecussion) {
            Intent i = new Intent(RescueHomepage.this, AddPrecusion.class);
            startActivity(i);

        }

        return true;
    }
}