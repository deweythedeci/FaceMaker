package com.example.facemaker;

public class FaceController {

    public Face face;
    public FaceModel model;

    public FaceController(Face face){
        this.face = face;
        model = face.model;
    }

}
