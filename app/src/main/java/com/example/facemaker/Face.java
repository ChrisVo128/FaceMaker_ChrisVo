// author Christopher Vo

package com.example.facemaker;

import android.graphics.Canvas;

import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;


    public Face() {
        randomize();
    }

    public void randomize() {
        Random random = new Random();

        skinColor = random.nextInt(256);

        eyeColor = random.nextInt(256);

        hairColor = random.nextInt(256);

        hairStyle = random.nextInt(256);

    }

    public void onDraw(Canvas canvas) {

    }

    public int getSkinColor() {
        return skinColor;
    }


    public int getEyeColor() {
        return eyeColor;
    }

    public int getHairColor() {
        return hairColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }
    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }
}


