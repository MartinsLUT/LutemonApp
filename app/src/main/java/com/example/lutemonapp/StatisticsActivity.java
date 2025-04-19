package com.example.lutemonapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {
    TextView totalLutemons, totalBattles, totalTrainings;
    LinearLayout lutemonStatsList;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        totalLutemons = findViewById(R.id.textView9);
        totalBattles = findViewById(R.id.textView10);
        totalTrainings = findViewById(R.id.textView11);
        lutemonStatsList = findViewById(R.id.lutemonsAtHome);

        totalLutemons.setText("Total Lutemons created: " + Lutemon.getNumberOfCreatedLutemons());
        totalBattles.setText("Total Battles: " + BattleField.getNumberOfBattles());
        totalTrainings.setText("Total Training Sessions: " + TrainingArea.getNumberOfTrainingSessions());

        for (Lutemon lutemon : Storage.getAllLutemons().values()) {
            View lutemonStats = getLayoutInflater().inflate(R.layout.stats_layout, null);
            TextView lutemonName = lutemonStats.findViewById(R.id.lutemonName);
            TextView lutemonColor = lutemonStats.findViewById(R.id.lutemonColor);
            View lutemonColorCircle = lutemonStats.findViewById(R.id.lutemonColorCircle);
            TextView battleCount = lutemonStats.findViewById(R.id.battleCount);
            TextView winsCount = lutemonStats.findViewById(R.id.winsCount);
            TextView trainingCount = lutemonStats.findViewById(R.id.trainingCount);
            lutemonName.setText(lutemon.name);
            lutemonColor.setText(lutemon.color);
            battleCount.setText(String.valueOf(lutemon.battles));
            winsCount.setText(String.valueOf(lutemon.wins));
            trainingCount.setText(String.valueOf(lutemon.trainings));

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
            lutemonColorCircle.setBackgroundResource(colorId);
            lutemonStatsList.addView(lutemonStats);
        }
    }
    public void goHomeBtn(View view){
        finish();
    }
}
