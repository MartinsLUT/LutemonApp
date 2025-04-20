package com.example.lutemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    Button createLutemonBtn, viewHomeBtn, viewBattleArenaBtn, viewTrainingAreaBtn, viewStatisticsBtn;
    Button save;
    TextView lutemonHome, lutemonBattlle, lutemonTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_main);

        lutemonHome = findViewById(R.id.lutemonHome);
        lutemonBattlle = findViewById(R.id.lutemonBattlle);
        lutemonTraining = findViewById(R.id.lutemonHome2);
        updateInfo();

        createLutemonBtn = findViewById(R.id.newLutemon);
        viewHomeBtn = findViewById(R.id.viewHome);
        viewBattleArenaBtn = findViewById(R.id.viewBattleArena);
        viewTrainingAreaBtn = findViewById(R.id.viewTrainingArea);
        viewStatisticsBtn = findViewById(R.id.viewStatistics);
        save = findViewById(R.id.save);

        //button function for if want to go to different view
        createLutemonBtn.setOnClickListener(v -> startActivity(new Intent(this, CreateNewLutemonActivity.class)));
        viewHomeBtn.setOnClickListener(v -> startActivity(new Intent(this, HomeLutemonActivity.class)));
        viewBattleArenaBtn.setOnClickListener(v -> startActivity(new Intent(this, BattleArenaActivity.class)));
        viewTrainingAreaBtn.setOnClickListener(v -> startActivity(new Intent(this, TrainingActivity.class)));
        viewStatisticsBtn.setOnClickListener(v -> startActivity(new Intent(this, StatisticsActivity.class)));
    }

    private void updateInfo() {
        lutemonHome.setText("You have " + Home.getNumberOfLutemonsAtHome() + " Lutemons at home");
        lutemonBattlle.setText("You have " + BattleField.getNumberOfLutemonsAtBattle() + " Lutemons ready");
        lutemonTraining.setText("You have " + TrainingArea.getNumberOfLutemonsAtTrainingArea() + " Lutemons training");
    }
    public void saveFileBtn(View view){
        if (Home.getNumberOfLutemonsAtHome() == 0) {
            save.setError("You have no lutemons to save!");
            return;
        }
        Storage.saveApp(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void loadFileBtn(View view){
        Storage.loadApp(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void resetGame(View view) {
        //this resets the saved file
        Storage.clearFile(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}













