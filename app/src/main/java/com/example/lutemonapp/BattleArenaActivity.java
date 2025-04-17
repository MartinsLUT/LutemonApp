package com.example.lutemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class BattleArenaActivity extends AppCompatActivity {
    LinearLayout lutemonLayout1, lutemonLayout2, lutemonArrow;
    LinearLayout battleResult;
    Button nextAttack, endBattle;
    Lutemon lutemon1, lutemon2;
    View lutemonArrowLeft, lutemonArrowRight;
    int iter = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);

        lutemonLayout1 = findViewById(R.id.lutemon1);
        lutemonLayout2 = findViewById(R.id.lutemon2);
        battleResult = findViewById(R.id.battleResult);
        nextAttack = findViewById(R.id.nextAttack);
        endBattle = findViewById(R.id.endBattle);
        //lutemonArrow = findViewById(R.id.lutemonArrow);
        //lutemonArrowLeft = getLayoutInflater().inflate(R.layout.arrow_left, null);
        //lutemonArrowRight = getLayoutInflater().inflate(R.layout.arrow_right, null);



        HashMap<Integer, Lutemon> lutemons = Home.listOfLutemonsAtBattleField();
        lutemon1 = lutemons.get(0);
        lutemon2 = lutemons.get(1);

        if (lutemon1 != null) {
            showLutemons(lutemon1, lutemonLayout1);
        }
        if (lutemon2 != null) {
            showLutemons(lutemon2, lutemonLayout2);
        }




    }

    private void showLutemons(Lutemon lutemon, LinearLayout layout) {
        TextView textView = new TextView(this);
        LinearLayout lutemonLayout = new LinearLayout(this);
        lutemonLayout.setOrientation(LinearLayout.VERTICAL);
        lutemonLayout.setPadding(10, 10, 10, 10);
        lutemonLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView imageView = new ImageView(this);
        int lutemonColor = 0;
        switch (lutemon.color) {
            case "White":
                lutemonColor = R.drawable.circle_white;
                break;
            case "Green":
                lutemonColor = R.drawable.circle_green;
                break;
            case "Pink":
                lutemonColor = R.drawable.circle_pink;
                break;
            case "Black":
                lutemonColor = R.drawable.circle_black;
                break;
            case "Orange":
                lutemonColor = R.drawable.circle_orange;
                break;

        }
        imageView.setImageResource(lutemonColor);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

        textView.setText(lutemon.name + "\nHP: " + lutemon.health + "/" + lutemon.maxHealth + "\nATK: " + lutemon.attack + " DEF: " + lutemon.defense + " EXP: " + lutemon.experience);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        lutemonLayout.addView(imageView);
        lutemonLayout.addView(textView);
        layout.addView(lutemonLayout);


    }

    public void battleBtn(View view) {
        if (lutemon1 == null || lutemon2 == null){
            nextAttack.setError("Need Two Lutemons!");
            return;
        }
        if (battleResult.toString().contains("wins")){

            nextAttack.setError("Fith Over!");
            return;
        }
        else if (lutemon1 == null || lutemon2 == null){
            nextAttack.setError("Need Two Lutemons!");
            return;
        }

        TextView attackInfo = new TextView(this);
        TextView resultInfo = new TextView(this);
        TextView lutemon1Health = new TextView(this);
        TextView lutemon2Health = new TextView(this);



        battleResult.removeAllViews();
        attackInfo.setText(lutemon1.color + " (" + lutemon1.name + ") attacks " + lutemon2.color + " (" + lutemon2.name + ").");
        battleResult.addView(attackInfo);

        int result = BattleField.fight(lutemon1, lutemon2);
        if (result == 1) {
            lutemonLayout1.removeAllViews();
            lutemonLayout2.removeAllViews();
            battleResult.removeAllViews();
            resultInfo.setText(lutemon1.color + " (" + lutemon1.name + ") wins!");
            battleResult.addView(resultInfo);

            if (lutemon1 != null) {
                Home.moveLutemonToHomeFromBattle(lutemon1.id);
            }
            if (lutemon2 != null) {
                Home.moveLutemonToHomeFromBattle(lutemon2.id);
            }
            lutemon1 = null;
            lutemon2 = null;
            }

        else if (result == 0){
            resultInfo.setText(lutemon2.color + " (" + lutemon2.name + ") escapes death!");
            battleResult.addView(resultInfo);

            lutemonLayout1.removeAllViews();
            lutemonLayout2.removeAllViews();

            if (iter%2 == 0){
                lutemon1Health.setText(lutemon1.color + " (" + lutemon1.name + ") health: " + lutemon1.health + "/" + lutemon1.maxHealth);
                lutemon2Health.setText(lutemon2.color + " (" + lutemon2.name + ") health: " + lutemon2.health + "/" + lutemon2.maxHealth);
                showLutemons(lutemon1, lutemonLayout1);
                showLutemons(lutemon2, lutemonLayout2);
                //lutemonArrow.addView(lutemonArrowRight);
            }
            else {
                showLutemons(lutemon2, lutemonLayout1);
                showLutemons(lutemon1, lutemonLayout2);
                lutemon2Health.setText(lutemon1.color + " (" + lutemon1.name + ") health: " + lutemon1.health + "/" + lutemon1.maxHealth);
                lutemon1Health.setText(lutemon2.color + " (" + lutemon2.name + ") health: " + lutemon2.health + "/" + lutemon2.maxHealth);
                //lutemonArrow.addView(lutemonArrowLeft);
            }
            battleResult.addView(lutemon1Health);
            battleResult.addView(lutemon2Health);

            Lutemon temp = lutemon1;
            lutemon1 = lutemon2;
            lutemon2 = temp;
            iter++;
        }
    }

    public void endBattle(View view){
        if (lutemon1 != null) {
            Home.moveLutemonToHomeFromBattle(lutemon1.id);
        }
        if (lutemon2 != null) {
            Home.moveLutemonToHomeFromBattle(lutemon2.id);
        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void backBtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
