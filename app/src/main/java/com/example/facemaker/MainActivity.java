package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes Hair Style Spinner
        String[] hairStyleOptions = new String[] {
                "Bowl Cut", "Spiky >:)", "Long Hair", "Bald"
        };
    }
}
