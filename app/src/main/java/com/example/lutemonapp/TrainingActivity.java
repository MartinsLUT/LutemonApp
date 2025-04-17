package com.example.lutemonapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button moveHome, trainBtn, xpBtn;
    Lutemon selectedLutemon;
    LinearLayout lutemonTraining;
    View lutemonFigure;
    TextView lutemonProgress;
    ArrayList<RadioButton> checkedLutemon = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_area);


        radioGroup = findViewById(R.id.trainingLutemons);
        moveHome = findViewById(R.id.moveHome);
        trainBtn = findViewById(R.id.trainSelected);
        xpBtn = findViewById(R.id.xpBtn);
        lutemonTraining = findViewById(R.id.lutemonTrain);
        lutemonFigure = getLayoutInflater().inflate(R.layout.lutemon_figure, null);
        lutemonProgress = lutemonFigure.findViewById(R.id.lutemonTrainingProgress);



        showLutemons();


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

        for(Lutemon lutemon : Home.listOfLutemonsAtTrainingArea()){
            View lutemonRadioBtn = LayoutInflater.from(this).inflate(R.layout.lutemon_radiobtn, null);

            //RadioButton radioButton = new RadioButton(this);
            RadioButton radioButton = lutemonRadioBtn.findViewById(R.id.lutemonRadioButton);
            radioButton.setId(lutemon.getId());
            checkedLutemon.add(radioButton);





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
            radioGroup.addView(lutemonRadioBtn);







            //setOnClickListener for radio button



            //Add this layout to radio button and the button to the radio group


        }
    }
    public int getCheckedButton(View view) {

        return radioGroup.getCheckedRadioButtonId();
    }

    public void trainButton(View view) {
        for (int i = 0; i < checkedLutemon.size(); i++) {
            if (checkedLutemon.get(i).isChecked()) {
                radioGroup.check(checkedLutemon.get(i).getId());
                //selectedLutemon = Home.listOfLutemonsAtTrainingArea().get(radioGroup.getCheckedRadioButtonId());
            }

        }
        try {
            selectedLutemon = Home.listOfLutemonsAtTrainingArea().get(radioGroup.getCheckedRadioButtonId());
        } catch (IndexOutOfBoundsException e) {
            selectedLutemon = null;
        }
        //selectedLutemon = Home.listOfLutemonsAtTrainingArea().get(radioGroup.getCheckedRadioButtonId());

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

    public void moveHomeBtn(View view) {
        if (lutemonProgress.getText().equals("Training in progress ...")) {
            moveHome.setError("Train first!");
            return;
        } else {
            for (int i = 0; i < checkedLutemon.size(); i++) {
                if (checkedLutemon.get(i).isChecked()) {
                    radioGroup.check(checkedLutemon.get(i).getId());
                }
            }
        }
        selectedLutemon = Home.listOfLutemonsAtTrainingArea().get(radioGroup.getCheckedRadioButtonId());
        if (selectedLutemon != null) {
            Home.moveLutemonToHomeFromTraining(selectedLutemon.id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
