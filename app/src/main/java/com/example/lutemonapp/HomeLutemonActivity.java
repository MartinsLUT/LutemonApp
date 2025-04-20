package com.example.lutemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeLutemonActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button moveToBattle, moveToTraining, backBtn;
    ArrayList<RadioButton> checkedLutemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        radioGroup = findViewById(R.id.radioGroup);
        moveToBattle = findViewById(R.id.moveBattle);
        moveToTraining = findViewById(R.id.moveTraining);
        showLutemons();

    }
    public void moveToBattle (View view) {
        for (int i = 0; i < checkedLutemon.size(); i++) {
            if (checkedLutemon.get(i).isChecked()) {
                radioGroup.check(checkedLutemon.get(i).getId());
            }
        }
        Lutemon selectedLutemon = Storage.getLutemonsAtHome().get(radioGroup.getCheckedRadioButtonId());
        if (selectedLutemon != null && BattleField.getNumberOfLutemonsAtBattle() < 2) {
            Storage.moveLutemonToBattleField(selectedLutemon.getId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            moveToBattle.setError("Select a lutemon!");
        }
    }
    public void moveToTraining(View view){
        for (int i = 0; i < checkedLutemon.size(); i++) {
            if (checkedLutemon.get(i).isChecked()) {
                radioGroup.check(checkedLutemon.get(i).getId());
            }
        }
        Lutemon selectedLutemon = Storage.getLutemonsAtHome().get(radioGroup.getCheckedRadioButtonId());
        if (selectedLutemon != null && TrainingArea.getNumberOfLutemonsAtTrainingArea() < 1) {
            Storage.moveLutemonToTrainingArea(selectedLutemon.getId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            moveToTraining.setError("Select a lutemon!");
        }
    }


    private void showLutemons() {
        radioGroup.removeAllViews();
        for(Lutemon lutemon : Storage.getLutemonsAtHome().values()){
            View lutemonRadioBtn = LayoutInflater.from(this).inflate(R.layout.lutemon_radiobtn, null);

            RadioButton radioButton = lutemonRadioBtn.findViewById(R.id.lutemonRadioButton);
            radioButton.setId(lutemon.getId());
            radioButton.setOnClickListener(v -> {
                for (RadioButton rb : checkedLutemon) {
                    rb.setChecked(false);
                }
                radioButton.setChecked(true);
            });
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
        }

    }
    public void backBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
