package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Code for getInstance taken from https://stackoverflow.com/a/47241833
    private static MainActivity instance;
    private FaceController faceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes Face Controller
        Face face = findViewById(R.id.faceSurfView);
        faceController = new FaceController(face, this);

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

    public static MainActivity getInstance(){
        return instance;
    }

}
