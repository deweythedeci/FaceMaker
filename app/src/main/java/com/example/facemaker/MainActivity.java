package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes Hair Style Spinner
        //Code adapted from
        // https://stackoverflow.com/questions/5241660/how-to-add-items-to-a-spinner-in-android
        String[] hairStyleOptions = new String[] {
                "Bowl Cut", "Spiky >:)", "Long Hair", "Bald"
        };
        Spinner hairStyleSpinner = (Spinner) findViewById(R.id.hairStyleSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, hairStyleOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairStyleSpinner.setAdapter(adapter);

        //Initializes Face Controller
        Face face = findViewById(R.id.faceSurfView);
        FaceController faceController = new FaceController(face);

    }
}
