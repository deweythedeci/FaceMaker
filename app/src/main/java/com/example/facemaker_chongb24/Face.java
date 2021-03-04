//@author Benjamin Chong

package com.example.facemaker_chongb24;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    //Constants to describe how face is drawn
    private static final float faceWidth = 1000.0f;
    private static final float foreheadHeight = 400.0f;
    private static final float faceHeight = 1300.0f;
    private static final float chinHeight = 400.0f;
    private static final float chinWidth = 400.0f;
    private static final float[] eyePosition = {0.0f, -50.0f};
    private static final float eyeSeparation = 250.0f;
    private static final float eyeWidth = 75.0f;
    private static final float eyeHeight = 50.0f;
    private static final float pupilSize = 30.0f;
    private static final float[] nosePosition = {0.0f, 120.0f};
    private static final float noseSize = 40.0f;
    private static final float[] mouthPosition = {0.0f, 400.0f};
    private static final float mouthWidth = 200.0f;

    private static final float x = 400.0f;
    private static final float y = 400.0f;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Face(Context context, AttributeSet attrs){

        super(context, attrs);
        setWillNotDraw(false);
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

        //Sets background color
        setBackgroundColor(Color.WHITE);

    }

    //Randomize face details
    public void randomize(){

        model.skinColor = numGenerator.nextInt(16777216);
        model.skinColor += 0xFF000000;
        model.eyeColor = numGenerator.nextInt(16777216);
        model.eyeColor += 0xFF000000;
        model.hairColor = numGenerator.nextInt(16777216);
        model.hairColor += 0xFF000000;
        model.hairStyle = numGenerator.nextInt(4);

    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onDraw(Canvas canvas) {

        //Sets paints to the correct color
        skinPaint.setColor(model.skinColor);
        irisPaint.setColor(model.eyeColor);
        hairPaint.setColor(model.hairColor);

        //Draws face using helper functions
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
                canvas.drawRect(x, y + faceHeight - chinHeight,
                        x + faceWidth, y + faceHeight * 1.1f, hairPaint);
        }
    }

    //Draws the hair in front of your head
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawHairFront(Canvas canvas){
        switch(model.hairStyle) {

            //Bowl Cut
            case 0:
                canvas.drawArc(x - faceWidth * 0.05f, y - foreheadHeight * 0.1f,
                        x + faceWidth * 1.05f, y + foreheadHeight * 2.1f,
                        0f, -180f, true, hairPaint);
                break;

            //Spiky Hair
            case 1:
                //This is more complicated so we call a helper function
                drawSpikyHair(canvas, 10, 100.0f);
                break;

            //Long Hair
            case 2:
                canvas.drawArc(x, y, x + faceWidth, y + foreheadHeight * 2,
                        0f, -180f, true, hairPaint);
                break;

        }
    }

    private void drawSpikyHair(Canvas canvas, int spikes, float spikeHeight){

        //Determines the angle needed between each spike
        double increment = Math.toRadians(180) / spikes;

        //Initiates path to draw hair
        Path p = new Path();
        p.moveTo(x + faceWidth, y + foreheadHeight);

        //Goes across the arc of the head and creates a jigsaw pattern using some math
        for(double angle = 0; angle < Math.toRadians(180); angle += increment){
            p.lineTo(x + (float) ((1 + spikeHeight/faceWidth) * Math.cos(angle + increment/2) + 1) * faceWidth/2,
                    y - (float) ((1 + spikeHeight/foreheadHeight) * Math.sin(angle + increment/2) - 1) * foreheadHeight);
            p.lineTo(x + (float) (Math.cos(angle + increment) + 1) * faceWidth/2,
                    y - (float) (Math.sin(angle + increment) - 1) * foreheadHeight);
        }

        //Closes and draws path
        p.close();
        canvas.drawPath(p, hairPaint);

    }

    //Draw the general shape of your face
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawFaceShape(Canvas canvas){

        //Starts path to outline face
        Path p = new Path();
        p.moveTo(x, y + faceHeight - chinHeight);

        //Draws left edge of face
        p.lineTo(x, y + foreheadHeight);

        //Draws the curved top of the head
        p.arcTo(x, y, x + faceWidth, y + 2 * foreheadHeight,
                0, -180, false);

        //Draws right edge of the face
        p.lineTo(x + faceWidth, y + foreheadHeight);

        //Draws chin
        p.lineTo(x + faceWidth, y + faceHeight - chinHeight);
        p.lineTo(x + faceWidth / 2 + chinWidth / 2, y + faceHeight);
        p.lineTo(x + faceWidth / 2 - chinWidth / 2, y + faceHeight);

        //Closes and draws path
        p.close();
        canvas.drawPath(p, skinPaint);
    }

    //Draws features on your face (nose and mouth)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawFaceFeatures(Canvas canvas){

        //Draws nose
        canvas.drawArc(x + faceWidth / 2 + nosePosition[0] - noseSize,
                y + faceHeight / 2 + nosePosition[1] - noseSize,
                x + faceWidth / 2 + nosePosition[0] + noseSize,
                y + faceHeight / 2 + nosePosition[1] + noseSize,
                90, 180, false, featuresPaint);

        //Draws mouth
        canvas.drawLine(x + faceWidth/2 + mouthPosition[0] - mouthWidth/2,
                        y + faceHeight/2 + mouthPosition[1],
                        x + faceWidth/2 + mouthPosition[0] + mouthWidth/2,
                        y + faceHeight/2 + mouthPosition[1],
                        featuresPaint);
    }

    //Draws your eyes
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawEyes(Canvas canvas){

        //draws whites of eyes
        canvas.drawOval(x + faceWidth/2 + eyePosition[0] - eyeSeparation - eyeWidth,
                y + faceHeight/2 + eyePosition[1] - eyeHeight,
                x + faceWidth/2 + eyePosition[0] - eyeSeparation + eyeWidth,
                y + faceHeight/2 + eyePosition[1] + eyeHeight,
                eyeWhitePaint);
        canvas.drawOval(x + faceWidth/2 + eyePosition[0] + eyeSeparation - eyeWidth,
                y + faceHeight/2 + eyePosition[1] - eyeHeight,
                x + faceWidth/2 + eyePosition[0] + eyeSeparation + eyeWidth,
                y + faceHeight/2 + eyePosition[1] + eyeHeight,
                eyeWhitePaint);

        //draws irises
        canvas.drawCircle(x + faceWidth/2 + eyePosition[0] - eyeSeparation,
                y + faceHeight/2 + eyePosition[1], eyeHeight, irisPaint);
        canvas.drawCircle(x + faceWidth/2 + eyePosition[0] + eyeSeparation,
                y + faceHeight/2 + eyePosition[1], eyeHeight, irisPaint);

        //draws pupils
        canvas.drawCircle(x + faceWidth/2 + eyePosition[0] - eyeSeparation,
                y + faceHeight/2 + eyePosition[1], pupilSize, pupilPaint);
        canvas.drawCircle(x + faceWidth/2 + eyePosition[0] + eyeSeparation,
                y + faceHeight/2 + eyePosition[1], pupilSize, pupilPaint);

        //draw outline of eyes
        canvas.drawOval(x + faceWidth/2 + eyePosition[0] - eyeSeparation - eyeWidth,
                        y + faceHeight/2 + eyePosition[1] - eyeHeight,
                        x + faceWidth/2 + eyePosition[0] - eyeSeparation + eyeWidth,
                        y + faceHeight/2 + eyePosition[1] + eyeHeight,
                        featuresPaint);
        canvas.drawOval(x + faceWidth/2 + eyePosition[0] + eyeSeparation - eyeWidth,
                        y + faceHeight/2 + eyePosition[1] - eyeHeight,
                        x + faceWidth/2 + eyePosition[0] + eyeSeparation + eyeWidth,
                        y + faceHeight/2 + eyePosition[1] + eyeHeight,
                        featuresPaint);

    }

}
