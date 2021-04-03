package com.demo.heropath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeroName;
    private Spinner chooseRace;
    private Spinner chooseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHeroName = findViewById(R.id.editTextHeroName);
        chooseRace = findViewById(R.id.chooseRace);
        chooseClass = findViewById(R.id.chooseClass);
    }

    public void startAdventure(View view) {
        String heroName = editTextHeroName.getText().toString();
        int heroRace = chooseRace.getSelectedItemPosition();
        int heroClass = chooseClass.getSelectedItemPosition();
        Intent intent = new Intent(this, StartingArea.class);
        intent.putExtra("name", heroName);
        intent.putExtra("race", heroRace);
        intent.putExtra("class", heroClass);
        startActivity(intent);
    }
}