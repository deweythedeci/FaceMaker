package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;
import androidx.annotation.RequiresApi;
import java.util.Random;

public class Face extends SurfaceView {

    //Contains details for drawing face
    public FaceModel model = new FaceModel();

    //Random number generator
    private Random numGenerator = new Random();

    //Paints for drawing face
    private Paint skinPaint = new Paint();
    private Paint eyeWhitePaint = new Paint();
    private Paint irisPaint = new Paint();
    private Paint pupilPaint = new Paint();
    private Paint hairPaint = new Paint();
    private Paint featuresPaint = new Paint();

    //Bounds of the Surface View
    Rect bounds = new Rect();

    public Face(Context context, AttributeSet attrs){

        super(context, attrs);
        randomize();

        //Initializes face paints
        skinPaint.setStyle(Paint.Style.FILL);
        eyeWhitePaint.setStyle(Paint.Style.FILL);
        eyeWhitePaint.setColor(Color.WHITE);
        irisPaint.setStyle(Paint.Style.FILL);
        pupilPaint.setStyle(Paint.Style.FILL);
        pupilPaint.setColor(Color.BLACK);
        hairPaint.setStyle(Paint.Style.FILL);
        featuresPaint.setStyle(Paint.Style.STROKE);
        featuresPaint.setColor(Color.BLACK);
        featuresPaint.setStrokeWidth(15);

    }

    //Randomize face details
    private void randomize(){
        this.model.skinColor = numGenerator.nextInt(16777216);
        this.model.eyeColor = numGenerator.nextInt(16777216);
        this.model.hairColor = numGenerator.nextInt(16777216);
        this.model.hairStyle = numGenerator.nextInt(4);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void onDraw(Canvas canvas) {

        //Sets paints to the correct color
        skinPaint.setColor(model.skinColor);
        irisPaint.setColor(model.eyeColor);
        hairPaint.setColor(model.hairColor);

        //Gets the bounds of the Surface View
        setClipBounds(bounds);

        //Draws face by calling helper functions
        drawHairBack(canvas);
        drawFaceShape(canvas);
        drawEyes(canvas);
        drawFaceFeatures(canvas);
        drawHairFront(canvas);

    }

    //Draws the hair that is behind your hair (as in long hair)
    private void drawHairBack(Canvas canvas){
        switch(model.hairStyle) {
            case 2:

        }
    }

    //Draws the hair in front of your head
    private void drawHairFront(Canvas canvas){
        switch(model.hairStyle) {
            case 1:

                break;

            case 2:

                break;

            case 3:

        }
    }

    //Draw the general shape of your face
    private void drawFaceShape(Canvas canvas){

    }

    //Draws features on your face (nose and mouth)
    private void drawFaceFeatures(Canvas canvas){

    }

    //Draws your eyes
    private void drawEyes(Canvas canvas){

    }

}
