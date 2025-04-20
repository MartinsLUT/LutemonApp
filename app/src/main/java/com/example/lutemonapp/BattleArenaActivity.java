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
        lutemonArrow = findViewById(R.id.battleArrow);
        lutemonArrowLeft = getLayoutInflater().inflate(R.layout.arrow_left_layout, null);
        lutemonArrowRight = getLayoutInflater().inflate(R.layout.arrow_right_layout, null);
        //from lutemons in battle field hashmap it sets one to lutemon1 other to lutemon2
        HashMap<Integer, Lutemon> lutemons = Storage.getLutemonsAtBattleField();
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon1 == null) {
                lutemon1 = lutemon;
            } else {
                lutemon2 = lutemon;
            }
        }
        //displaying lutemons
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
        //displaying battle info
        TextView attackInfo = new TextView(this);
        TextView resultInfo = new TextView(this);
        TextView lutemon1Health = new TextView(this);
        TextView lutemon2Health = new TextView(this);
        battleResult.removeAllViews();
        attackInfo.setText(lutemon1.color + " (" + lutemon1.name + ") attacks " + lutemon2.color + " (" + lutemon2.name + ").");
        battleResult.addView(attackInfo);

        int result = BattleField.fight(lutemon1, lutemon2);
        if (result == 1) {
            //if lutemon2 health is <= 0 then lutemon1 wins
            lutemonLayout1.removeAllViews();
            lutemonLayout2.removeAllViews();
            lutemonArrow.removeAllViews();
            battleResult.removeAllViews();
            //displaying result
            resultInfo.setText(lutemon1.color + " (" + lutemon1.name + ") wins!");
            battleResult.addView(resultInfo);
            showLutemons(lutemon1, lutemonLayout1);

            //send lutemon2 to home

            if (lutemon2 != null) {
                Storage.moveLutemonToHomeFromBattle(lutemon2.id);
                //reset health
                lutemon2.health = lutemon2.maxHealth;
            }
            //reset lutemon2 because it lost
            lutemon2 = null;
            iter = 0;
            }

        else if (result == 0){
            //if both lutemons are still alive
            resultInfo.setText(lutemon2.color + " (" + lutemon2.name + ") escapes death!");
            battleResult.addView(resultInfo);

            lutemonLayout1.removeAllViews();
            lutemonLayout2.removeAllViews();
            lutemonArrow.removeAllViews();

            if (iter%2 == 0){
                lutemon1Health.setText(lutemon1.color + " (" + lutemon1.name + ") health: " + lutemon1.health + "/" + lutemon1.maxHealth);
                lutemon2Health.setText(lutemon2.color + " (" + lutemon2.name + ") health: " + lutemon2.health + "/" + lutemon2.maxHealth);
                showLutemons(lutemon1, lutemonLayout1);
                showLutemons(lutemon2, lutemonLayout2);
                lutemonArrow.addView(lutemonArrowRight);
            }
            else {
                showLutemons(lutemon2, lutemonLayout1);
                showLutemons(lutemon1, lutemonLayout2);
                lutemon2Health.setText(lutemon1.color + " (" + lutemon1.name + ") health: " + lutemon1.health + "/" + lutemon1.maxHealth);
                lutemon1Health.setText(lutemon2.color + " (" + lutemon2.name + ") health: " + lutemon2.health + "/" + lutemon2.maxHealth);
                lutemonArrow.addView(lutemonArrowLeft);
            }
            battleResult.addView(lutemon1Health);
            battleResult.addView(lutemon2Health);
            //swithc lutemon1 and lutemon2 places
            //lutemon1 is always the attacker lutemon2 the defender
            Lutemon temp = lutemon1;
            lutemon1 = lutemon2;
            lutemon2 = temp;
            iter++;
        }
    }

    public void endBattle(View view){
        //if end battle is selected it returns both lutemons home
        //resets the lutemon spots to empty
        //also if they have gotten damage from battle it stays like that till they get defeated or trained
        if (lutemon1 != null) {
            Storage.moveLutemonToHomeFromBattle(lutemon1.id);
            //reset health
            lutemon1.health = lutemon1.maxHealth;
            lutemon1 = null;
        }
        if (lutemon2 != null) {
            Storage.moveLutemonToHomeFromBattle(lutemon2.id);
            //reset health
            lutemon2.health = lutemon2.maxHealth;
            lutemon2 = null;
        }
        iter = 0;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void switchLutemons(View view){
        if (lutemon1 != null && lutemon2 != null) {
            lutemonLayout1.removeAllViews();
            lutemonLayout2.removeAllViews();
            lutemonArrow.removeAllViews();
            //switch lutemon1 and lutemon2 places
            Lutemon temp = lutemon1;
            lutemon1 = lutemon2;
            lutemon2 = temp;
            showLutemons(lutemon1, lutemonLayout1);
            showLutemons(lutemon2, lutemonLayout2);
        }
    }
    public void goHomeScreen(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
