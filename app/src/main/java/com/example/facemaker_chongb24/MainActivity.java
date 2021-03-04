//@author Benjamin Chong

package com.example.facemaker_chongb24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes Face Controller
        Face face = findViewById(R.id.faceSurfView);
        FaceController faceController = new FaceController(face, this);

        //Links Face Controller with controls
        Button randomizeButton = findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(faceController);

        RadioGroup colorRadioGroup = findViewById(R.id.colorRadioGroup);
        colorRadioGroup.setOnCheckedChangeListener(faceController);

        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        redSeekBar.setOnSeekBarChangeListener(faceController);
        blueSeekBar.setOnSeekBarChangeListener(faceController);
        greenSeekBar.setOnSeekBarChangeListener(faceController);

        Spinner hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        hairStyleSpinner.setOnItemSelectedListener(faceController);

    }

}
