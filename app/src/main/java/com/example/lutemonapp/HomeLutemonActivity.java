package com.example.lutemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class HomeLutemonActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button moveToBattle, moveToTraining, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        radioGroup = findViewById(R.id.radioGroup);
        moveToBattle = findViewById(R.id.moveBattle);
        moveToTraining = findViewById(R.id.moveTraining);
        backBtn = findViewById(R.id.button2);
        backBtn.setOnClickListener(v -> finish());


        showLutemons();


    }
    public void moveToBattle (View view) {
        Lutemon selectedLutemon = Home.listOfLutemonsAtHome().get(radioGroup.getCheckedRadioButtonId());
        if (selectedLutemon != null && BattleField.getNumberOfLutemonsAtBattle() < 2) {
            Home.moveLutemonToBattleField(selectedLutemon.getId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            moveToBattle.setError("Select a lutemon!");

        }
    }
    public void moveToTraining(View view){
        Lutemon selectedLutemon = Home.listOfLutemonsAtHome().get(radioGroup.getCheckedRadioButtonId());
        if (selectedLutemon != null && TrainingArea.getNumberOfLutemonsAtTrainingArea() < 1) {
            Home.moveLutemonToTrainingArea(selectedLutemon.getId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            moveToTraining.setError("Select a lutemon!");
        }
    }


    private void showLutemons() {
        for (Lutemon lutemon : Home.listOfLutemonsAtHome().values()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(lutemon.getId());


            radioButton.setText(lutemon.name + "\n" + lutemon.color + "\nATK: " + lutemon.attack + "DEF: " + lutemon.defense + "HP: " + lutemon.health + "/" + lutemon.maxHealth + "\nExperience: " + lutemon.experience);
            switch (lutemon.color) {
                case "White":
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_white, 0);
                    break;
                case "Green":
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_green, 0);
                    break;
                case "Pink":
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_pink,0);
                    break;
                case "Black":
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_black, 0);
                    break;
                case "Orange":
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_orange,0);
                    break;
            }

            radioGroup.addView(radioButton);
        }
    }
}
