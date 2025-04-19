package com.example.lutemonapp;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class CreateNewLutemonActivity extends AppCompatActivity {
    EditText newLutemonName;
    RadioButton createWhiteBtn;
    RadioButton createGreenBtn;
    RadioButton createPinkBtn;
    RadioButton createBlackBtn;
    RadioButton createOrangeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_lutemon);

        newLutemonName = findViewById(R.id.newLutemonName);

        createWhiteBtn = findViewById(R.id.createWhiteBtn);
        createGreenBtn = findViewById(R.id.createGreenBtn);
        createPinkBtn = findViewById(R.id.createPinkBtn);
        createBlackBtn = findViewById(R.id.createBlackBtn);
        createOrangeBtn = findViewById(R.id.createOrangeBtn);

    }

    public void cancleBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void newLutemon (View view) {
        String lutemonName = newLutemonName.getText().toString();

        if (lutemonName.isEmpty()) {
            newLutemonName.setError("Please enter a name");
            return;
        }

        Lutemon lutemon = null;
        if (createWhiteBtn.isChecked()) {
            lutemon = new White(lutemonName);
        } else if (createGreenBtn.isChecked()) {
            lutemon = new Green(lutemonName);
        } else if (createPinkBtn.isChecked()) {
            lutemon = new Pink(lutemonName);
        } else if (createBlackBtn.isChecked()) {
            lutemon = new Black(lutemonName);
        } else if (createOrangeBtn.isChecked()) {
            lutemon = new Orange(lutemonName);
        }

        if (lutemon != null) {
            Home.createLutemon(lutemon);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}










