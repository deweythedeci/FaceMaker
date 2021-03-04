package com.example.facemaker;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import java.util.Random;

public class FaceController implements SeekBar.OnSeekBarChangeListener, View.OnClickListener,
                                        RadioGroup.OnCheckedChangeListener, Spinner.OnItemSelectedListener{

    public Face face;
    public FaceModel model;

    //Tells FaceController which color radio button is selected
    // 0 = hair, 1 = skin, 2 = eyes
    public int colorRadioGroupIndex;

    //Used for FindViewById calls
    private Activity activity;

    //Random Number Generator
    private Random numGenerator = new Random();

    public FaceController(Face face, Activity activity){
        this.face = face;
        model = face.model;
        this.activity = activity;
        resetControls();
    }

    //Randomizes the face
    @Override
    public void onClick(View v) {
        face.randomize();
        face.invalidate();
        colorRadioGroupIndex = numGenerator.nextInt(3);
        resetControls();
    }

    //Sets all the controls to their correct values
    public void resetControls(){

        RadioGroup colorRadioGroup = activity.findViewById(R.id.colorRadioGroup);

        SeekBar redSeekBar = activity.findViewById(R.id.redSeekBar);
        SeekBar blueSeekBar = activity.findViewById(R.id.blueSeekBar);
        SeekBar greenSeekBar = activity.findViewById(R.id.greenSeekBar);

        int[] RGB;

        //Finds the part of the face that is currently selected
        switch (colorRadioGroupIndex){
            case 0:
                //Checks button in case it is on the wrong one
                colorRadioGroup.check(R.id.hairRadioButton);

                //Gets appropriate color and sets seek bars to the correct values
                RGB = getRGB(model.hairColor);
                redSeekBar.setProgress(RGB[0]);
                greenSeekBar.setProgress(RGB[1]);
                blueSeekBar.setProgress(RGB[2]);
                break;

            case 1:
                //Checks button in case it is on the wrong one
                colorRadioGroup.check(R.id.skinRadioButton);

                //Gets appropriate color and sets seek bars to the correct values
                RGB = getRGB(model.skinColor);
                redSeekBar.setProgress(RGB[0]);
                greenSeekBar.setProgress(RGB[1]);
                blueSeekBar.setProgress(RGB[2]);
                break;

            case 2:
                //Checks button in case it is on the wrong one
                colorRadioGroup.check(R.id.eyesRadioButton);

                //Gets appropriate color and sets seek bars to the correct values
                RGB = getRGB(model.eyeColor);
                redSeekBar.setProgress(RGB[0]);
                greenSeekBar.setProgress(RGB[1]);
                blueSeekBar.setProgress(RGB[2]);
                break;
        }

        Spinner hairStyleSpinner = activity.findViewById(R.id.hairStyleSpinner);
        hairStyleSpinner.setSelection(model.hairStyle);

    }

    //Changes the color of the selected part
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        //Gets the color of the currently selected part
        int RGB[] = new int[3];
        switch (colorRadioGroupIndex){
            case 0:
                RGB = getRGB(model.hairColor);
                break;

            case 1:
                RGB = getRGB(model.skinColor);
                break;

            case 2:
                RGB = getRGB(model.eyeColor);
                break;
        }

        //Updates the RGB value according to the progress bar
        if(seekBar == seekBar.findViewById(R.id.redSeekBar)){
            RGB[0] = progress;
        }
        else if(seekBar == seekBar.findViewById(R.id.greenSeekBar)){
            RGB[1] = progress;
        }
        else if(seekBar == seekBar.findViewById(R.id.blueSeekBar)){
            RGB[2] = progress;
        }

        //Sends updated RGB value to the model
        switch (colorRadioGroupIndex){
            case 0:
                model.hairColor = getHex(RGB);
                break;

            case 1:
                model.skinColor = getHex(RGB);
                break;

            case 2:
                model.eyeColor = getHex(RGB);
                break;
        }

        face.invalidate();

    }

    //Helper functions for dealing with color
    //Code taken from https://developer.android.com/reference/android/graphics/Color
    private int[] getRGB(int hex){
        int[] RGB = new int[3];
        RGB[0] = (hex >> 16) & 0xff;
        RGB[1] = (hex >> 8) & 0xff;
        RGB[2] = hex & 0xff;
        return RGB;
    }
    private int getHex(int[] RGB){
        return (0xff) << 24 | (RGB[0] & 0xff) << 16 | (RGB[1] & 0xff) << 8 | (RGB[2] & 0xff);
    }

    //Changes which color you are changing
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.hairRadioButton) {
            colorRadioGroupIndex = 0;
        }
        else if(checkedId == R.id.skinRadioButton){
            colorRadioGroupIndex = 1;
        }
        else if(checkedId == R.id.eyesRadioButton){
            colorRadioGroupIndex = 2;
        }
        resetControls();
    }

    //Changes which hair style you have
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        model.hairStyle = position;
        face.invalidate();
    }

    //Unused functions that are still required for implementation
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
