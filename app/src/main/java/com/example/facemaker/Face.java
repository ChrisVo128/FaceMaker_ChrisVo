// author Christopher Vo

package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

public class Face extends SurfaceView {
    // RGB values for hair, eye, and skin color
    private static int redHairColor = 0;
    private static int greenHairColor = 0;
    private static int blueHairColor = 0;
    private static int redEyeColor = 0;
    private static int greenEyeColor = 0;
    private static int blueEyeColor = 0;
    private static int redSkinColor = 0;
    private static int greenSkinColor = 0;
    private static int blueSkinColor = 0;
    private static int skinColor;
    private static int eyeColor;
    private static int hairColor;
    private static int hairStyle;


    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
        randomize();

        setBackgroundColor(Color.GRAY);
    }

    public void randomize() {
        Random random = new Random();

        // Generate random values for skin color, eye color, hair color, and hair style
        redSkinColor = random.nextInt(256);
        greenSkinColor = random.nextInt(256);
        blueSkinColor = random.nextInt(256);
        skinColor = Color.argb(255, redSkinColor, greenSkinColor, blueSkinColor);

        redEyeColor = random.nextInt(256);
        greenEyeColor = random.nextInt(256);
        blueEyeColor = random.nextInt(256);
        eyeColor = Color.argb(255, redEyeColor, greenEyeColor, blueEyeColor);

        redHairColor = random.nextInt(256);
        greenHairColor = random.nextInt(256);
        blueHairColor = random.nextInt(256);
        hairColor = Color.argb(255, redHairColor, greenHairColor, blueHairColor);

        // Randomize between 3 hairstyles
        hairStyle = random.nextInt(4);

        invalidate();

    }

    public int getRedHairColor() {
        return redHairColor;
    }

    public int getGreenHairColor() {
        return greenHairColor;
    }

    public int getBlueHairColor() {
        return blueHairColor;
    }

    public int getRedEyeColor() {
        return redEyeColor;
    }

    public int getGreenEyeColor() {
        return greenEyeColor;
    }

    public int getBlueEyeColor() {
        return blueEyeColor;
    }

    public int getRedSkinColor() {
        return redSkinColor;
    }

    public int getGreenSkinColor() {
        return greenSkinColor;
    }

    public int getBlueSkinColor() {
        return blueSkinColor;
    }

    public void setRedHairColor(int redHairColor) {
        Face.redHairColor = redHairColor;
    }

    public void setGreenHairColor(int greenHairColor) {
        Face.greenHairColor = greenHairColor;
    }

    public void setBlueHairColor(int blueHairColor) {
        Face.blueHairColor = blueHairColor;
    }

    public void setRedEyeColor(int redEyeColor) {
        Face.redEyeColor = redEyeColor;
    }

    public void setGreenEyeColor(int greenEyeColor) {
        Face.greenEyeColor = greenEyeColor;
    }

    public void setBlueEyeColor(int blueEyeColor) {
        Face.blueEyeColor = blueEyeColor;
    }

    public void setRedSkinColor(int redSkinColor) {
        Face.redSkinColor = redSkinColor;
    }

    public void setGreenSkinColor(int greenSkinColor) {
        Face.greenSkinColor = greenSkinColor;
    }

    public void setBlueSkinColor(int blueSkinColor) {
        Face.blueSkinColor = blueSkinColor;
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
        Face.skinColor = skinColor;
    }

    public void setEyeColor(int eyeColor) {
        Face.eyeColor = eyeColor;
    }

    public void setHairColor(int hairColor) {
        Face.hairColor = hairColor;
    }

    public void setHairStyle(int hairStyle) {
        Face.hairStyle = hairStyle;
    }

    public void onDraw(Canvas canvas) {

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw head (circle)
        Paint skinColorVal = new Paint();
        skinColorVal.setColor(skinColor);
        canvas.drawCircle(centerX, centerY, 400.0f, skinColorVal);

        // Draw eyes (circles)
        Paint eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(centerX - 120, centerY - 100, 40.0f, eyePaint); // left eye
        canvas.drawCircle(centerX + 120, centerY - 100, 40.0f, eyePaint); // right eye

        // Draw hair using rectangles based on the chosen hairstyles
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);
        if (hairStyle == 0) {
            // Bald haircut for "none" selected
        } else if (hairStyle == 1) {
            // Draw buzz hair style
            canvas.drawArc(centerX - 290, centerY - 400, centerX + 290, centerY - 150, 180, 180, true, hairPaint);
        } else if (hairStyle == 2) {
            // Draw Middle Part hair style
            canvas.drawArc(centerX - 400, centerY - 400, centerX + 400, centerY + 150, 180, 90, false, hairPaint);
            canvas.drawArc(centerX - 400, centerY - 400, centerX + 400, centerY + 150, 270, 90, false, hairPaint);
        } else if (hairStyle == 3) {
            // Draw afro hair style
            for (int i = 0; i < 4; i++) {  // use for loop to draw 4 circles consecutively
                canvas.drawCircle(centerX - 225.0f + (150 * i), centerY - 400.0f, 100, hairPaint);
            }

        }
    }
}





