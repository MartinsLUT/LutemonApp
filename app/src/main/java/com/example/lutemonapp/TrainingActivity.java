package com.example.lutemonapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button moveHome, trainBtn, xpBtn;
    Lutemon selectedLutemon;
    LinearLayout lutemonTraining;
    View lutemonFigure;
    TextView lutemonProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_area);


        radioGroup = findViewById(R.id.RadioGroup);
        moveHome = findViewById(R.id.moveHome);
        trainBtn = findViewById(R.id.trainSelected);
        xpBtn = findViewById(R.id.xpBtn);
        lutemonTraining = findViewById(R.id.lutemonTrain);
        lutemonFigure = getLayoutInflater().inflate(R.layout.lutemon_figure, null);
        lutemonProgress = lutemonFigure.findViewById(R.id.lutemonTrainingProgress);


        showLutemons();

        moveHome.setOnClickListener(v -> {
            Home.moveLutemonToHomeFromTraining(radioGroup.getCheckedRadioButtonId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });



        xpBtn.setOnClickListener(v -> {
            if (lutemonProgress.getText().equals("Training in progress ...")) {
                TrainingArea.addXp(selectedLutemon);
                Home.moveLutemonToHomeFromTraining(selectedLutemon.id);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                xpBtn.setError("Train first!");
            }
        });


    }

    private void showLutemons(){
        radioGroup.removeAllViews();
        for(Lutemon lutemon : Home.listOfLutemonsAtTrainingArea().values()){
            View lutemonRadioBtn = getLayoutInflater().inflate(R.layout.lutemon_radiobtn, null);
            RadioButton radioButton = lutemonRadioBtn.findViewById(R.id.lutemonRadioButton);
            TextView lutemonName = lutemonRadioBtn.findViewById(R.id.lutemonName);
            TextView lutemonColor = lutemonRadioBtn.findViewById(R.id.lutemonColor);
            TextView lutemonInfo = lutemonRadioBtn.findViewById(R.id.lutemonInfo);
            TextView lutemonExperience = lutemonRadioBtn.findViewById(R.id.lutemonExperience);
            View colorCircle = lutemonRadioBtn.findViewById(R.id.lutemonColorCircle);
            lutemonName.setText(lutemon.name);
            lutemonColor.setText(lutemon.color);
            lutemonInfo.setText("ATK: " + lutemon.attack + " DEF: " + lutemon.defense + " HP: " + lutemon.health + "/" + lutemon.maxHealth);
            lutemonExperience.setText("Experience: " + lutemon.experience);

            //radioButton.setText(lutemon.name + " " + lutemon.color);

            //radioButton.setText(lutemon.name + "\n" + lutemon.color + "\nATK: " + lutemon.attack + "DEF: " + lutemon.defense + "HP: " + lutemon.health + "/" + lutemon.maxHealth + "\nExperience: " + lutemon.experience);
            int colorId = 0;
            switch (lutemon.color) {
                case "White":
                    colorId = R.drawable.circle_white;
                    break;
                case "Green":
                    colorId = R.drawable.circle_green;
                    break;
                case "Pink":
                    colorId = R.drawable.circle_pink;
                    break;
                case "Black":
                    colorId = R.drawable.circle_black;
                    break;
                case "Orange":
                    colorId = R.drawable.circle_orange;
                    break;
            }
            colorCircle.setBackgroundResource(colorId);
            radioButton.setId(lutemon.getId());

            //Adding layout to radio button

            radioGroup.addView(lutemonRadioBtn);
            //setOnClickListener for radio button



            //Add this layout to radio button and the button to the radio group


        }
    }

    public void trainButton(View view) {
        selectedLutemon = Home.listOfLutemonsAtTrainingArea().get(radioGroup.getCheckedRadioButtonId());

        if (selectedLutemon != null && selectedLutemon.experience > 0) {
            TrainingArea.train(selectedLutemon);
            View colorCircle = lutemonFigure.findViewById(R.id.lutemonColorCircle);
            lutemonProgress.setText("Training in progress ...");
            lutemonTraining.addView(lutemonFigure);
            int colorId = 0;
            switch (selectedLutemon.color) {
                case "White":
                    colorId = R.drawable.circle_white;
                    break;
                case "Green":
                    colorId = R.drawable.circle_green;
                    break;
                case "Pink":
                    colorId = R.drawable.circle_pink;
                    break;
                case "Black":
                    colorId = R.drawable.circle_black;
                    break;
                case "Orange":
                    colorId = R.drawable.circle_orange;
                    break;
            }
            colorCircle.setBackgroundResource(colorId);
        } else {
            trainBtn.setError("Not enough experience!");
        }
    }

}
