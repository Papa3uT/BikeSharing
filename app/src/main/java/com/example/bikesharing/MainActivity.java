package com.example.bikesharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }


    public void OnClickLogin(View view) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(MainActivity.this , Authorization_Number.class));
        }
        else
        {
            startActivity(new Intent(MainActivity.this , MapsActivity.class));
            finish();
        }

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);

    }


}