// author Christopher Vo
package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity  extends AppCompatActivity {



    // Flag to prevent seek bar listener from triggering during updates
    boolean voidListener = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize seekbar for rgb
        SeekBar redBar = findViewById(R.id.redSeekBar);
        SeekBar greenBar = findViewById(R.id.greenSeekBar);
        SeekBar blueBar = findViewById(R.id.blueSeekBar);

        // Initialize text views for displaying RGB values
        TextView redView = findViewById(R.id.redVal);
        TextView greenView = findViewById(R.id.greenVal);
        TextView blueView = findViewById(R.id.blueVal);

        // Initialize the Face view
        Face faceView = findViewById(R.id.Faceview);

        // Initialize radio group and radio button for when the user selects hair, skin, or eyes
        RadioButton radioHair = findViewById(R.id.radio_Hair);
        RadioButton radioEyes = findViewById(R.id.radio_Eyes);
        RadioButton radioSkin = findViewById(R.id.radio_Skin);

        // Initialize the random button to display random faces
       Button randomFaceBt = findViewById(R.id.RandomButton);
        randomFaceBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               faceView.randomize();
               voidListener = true;
               if (radioHair.isChecked()) {
                   redBar.setProgress(faceView.getRedHairColor());
                   greenBar.setProgress(faceView.getGreenHairColor());
                   blueBar.setProgress(faceView.getBlueHairColor());
               } else if (radioEyes.isChecked()) {
                   redBar.setProgress(faceView.getRedEyeColor());
                   greenBar.setProgress(faceView.getGreenEyeColor());
                   blueBar.setProgress(faceView.getBlueEyeColor());
               } else if (radioSkin.isChecked()) {
                   redBar.setProgress(faceView.getRedSkinColor());
                   greenBar.setProgress(faceView.getGreenSkinColor());
                   blueBar.setProgress(faceView.getBlueSkinColor());
               }
               voidListener = false;
           }
       });


        // Listener for hair radio button
        radioHair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // Update seek bars when hair radio button is checked
                    voidListener = true;
                    redBar.setProgress(faceView.getRedHairColor());
                    greenBar.setProgress(faceView.getGreenHairColor());
                    blueBar.setProgress(faceView.getBlueHairColor());
                }
                voidListener = false;
            }
        });
        // Listener for eyes radio button
        radioEyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // Update seek bars when eyes radio button is checked
                    voidListener = true;
                    redBar.setProgress(faceView.getRedEyeColor());
                    greenBar.setProgress(faceView.getGreenEyeColor());
                    blueBar.setProgress(faceView.getBlueEyeColor());
                }
                voidListener = false;
            }
        });
        // Listener for skin radio button
        radioSkin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // Update seek bars when skin radio button is checked
                    voidListener = true;
                    redBar.setProgress(faceView.getRedSkinColor());
                    greenBar.setProgress(faceView.getGreenSkinColor());
                    blueBar.setProgress(faceView.getBlueSkinColor());
                }
                voidListener = false;
            }
        });

            // Initialize spinner for selecting hairstyle
            Spinner spinner = findViewById(R.id.spinner_HairStyle);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Display a toast message when a hairstyle is selected
                    String item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(MainActivity.this, "Selected Item: " + item, Toast.LENGTH_SHORT).show();
                    faceView.setHairStyle(i);
                    faceView.invalidate();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            // Populate spinner with hairstyle options
            ArrayList<String> HairTypes = new ArrayList<>();
            HairTypes.add("None");
            HairTypes.add("Buzz");
            HairTypes.add("Middle Part");
            HairTypes.add("Afro");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, HairTypes);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            spinner.setAdapter(adapter);


            // Set up listeners for seek bars to update associated text views
            redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Update hair color based on SeekBar progress
                    redView.setText(String.valueOf(progress));
                    if(radioHair.isChecked() && !voidListener)
                    {
                        faceView.setRedHairColor(redBar.getProgress());
                        faceView.setGreenHairColor(greenBar.getProgress());
                        faceView.setBlueHairColor(blueBar.getProgress());
                        faceView.setHairColor(Color.argb(255, faceView.getRedHairColor(), faceView.getGreenHairColor(), faceView.getBlueHairColor()));
                    }else if(radioEyes.isChecked() && !voidListener)
                    {
                        faceView.setRedEyeColor(redBar.getProgress());
                        faceView.setGreenEyeColor(greenBar.getProgress());
                        faceView.setBlueEyeColor(blueBar.getProgress());
                        faceView.setEyeColor(Color.argb(255, faceView.getRedEyeColor(), faceView.getGreenEyeColor(), faceView.getBlueEyeColor()));
                    }else if(radioSkin.isChecked() && !voidListener){
                        faceView.setRedSkinColor(redBar.getProgress());
                        faceView.setGreenSkinColor(greenBar.getProgress());
                        faceView.setBlueSkinColor(blueBar.getProgress());
                        faceView.setSkinColor(Color.argb(255, faceView.getRedSkinColor(), faceView.getGreenSkinColor(), faceView.getBlueSkinColor()));
                    }
                    faceView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    greenView.setText(String.valueOf(progress));
                    if(radioHair.isChecked() && !voidListener)
                    {
                        faceView.setRedHairColor(redBar.getProgress());
                        faceView.setGreenHairColor(greenBar.getProgress());
                        faceView.setBlueHairColor(blueBar.getProgress());
                        faceView.setHairColor(Color.argb(255, faceView.getRedHairColor(), faceView.getGreenHairColor(), faceView.getBlueHairColor()));
                    }else if(radioEyes.isChecked() && !voidListener)
                    {
                        faceView.setRedEyeColor(redBar.getProgress());
                        faceView.setGreenEyeColor(greenBar.getProgress());
                        faceView.setBlueEyeColor(blueBar.getProgress());
                        faceView.setEyeColor(Color.argb(255, faceView.getRedEyeColor(), faceView.getGreenEyeColor(), faceView.getBlueEyeColor()));
                    }else if(radioSkin.isChecked() && !voidListener){
                        faceView.setRedSkinColor(redBar.getProgress());
                        faceView.setGreenSkinColor(greenBar.getProgress());
                        faceView.setBlueSkinColor(blueBar.getProgress());
                        faceView.setSkinColor(Color.argb(255, faceView.getRedSkinColor(), faceView.getGreenSkinColor(), faceView.getBlueSkinColor()));
                    }
                    faceView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    blueView.setText(String.valueOf(progress));
                    if(radioHair.isChecked() && !voidListener)
                    {
                        faceView.setRedHairColor(redBar.getProgress());
                        faceView.setGreenHairColor(greenBar.getProgress());
                        faceView.setBlueHairColor(blueBar.getProgress());
                        faceView.setHairColor(Color.argb(255, faceView.getRedHairColor(), faceView.getGreenHairColor(), faceView.getBlueHairColor()));
                    }else if(radioEyes.isChecked() && !voidListener)
                    {
                        faceView.setRedEyeColor(redBar.getProgress());
                        faceView.setGreenEyeColor(greenBar.getProgress());
                        faceView.setBlueEyeColor(blueBar.getProgress());
                        faceView.setEyeColor(Color.argb(255, faceView.getRedEyeColor(), faceView.getGreenEyeColor(), faceView.getBlueEyeColor()));
                    }else if(radioSkin.isChecked() && !voidListener){
                        faceView.setRedSkinColor(redBar.getProgress());
                        faceView.setGreenSkinColor(greenBar.getProgress());
                        faceView.setBlueSkinColor(blueBar.getProgress());
                        faceView.setSkinColor(Color.argb(255, faceView.getRedSkinColor(), faceView.getGreenSkinColor(), faceView.getBlueSkinColor()));
                    }
                    faceView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }
    }


