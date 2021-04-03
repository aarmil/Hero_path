package com.demo.heropath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class TavernActivity extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private boolean clothesInBlood;
    private boolean treasures;
    private boolean drunken;
    private boolean princess;
    private boolean princessDead;
    private boolean triedToGetPrincess;
    private int seconds = 30;
    private boolean gameOn = true;
    private boolean gameWasOn = true;
    private int countDrink = 0;
    String text;

    private TextView textViewTavernResult;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavern);
        Intent intent = getIntent();
        heroName = intent.getStringExtra("name");
        heroRace = intent.getIntExtra("race", 0);
        heroClass = intent.getIntExtra("class", 0);
        clothesInBlood = intent.getBooleanExtra("blood", false);
        treasures = intent.getBooleanExtra("treasures", false);
        drunken = intent.getBooleanExtra("drunken", false);
        princess = intent.getBooleanExtra("princess", false);
        princessDead = intent.getBooleanExtra("princessDead", false);
        triedToGetPrincess = intent.getBooleanExtra("triedToGetPrincess", false);
        textViewTavernResult = findViewById(R.id.textViewTavernResult);
        textViewTimer = findViewById(R.id.textViewTimer);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            countDrink = savedInstanceState.getInt("countDrink");
            gameOn = savedInstanceState.getBoolean("gameOn");
            gameWasOn = savedInstanceState.getBoolean("gameWasOn");
            text = savedInstanceState.getString("text");
            clothesInBlood = savedInstanceState.getBoolean("blood");
            treasures = savedInstanceState.getBoolean("treasures");
            drunken = savedInstanceState.getBoolean("drunken");
            princess = savedInstanceState.getBoolean("princess");
            princessDead = savedInstanceState.getBoolean("princessDead");
            triedToGetPrincess = savedInstanceState.getBoolean("triedToGetPrincess");
            textViewTavernResult.setText(text);
        }

        runTimer();
    }

    public void drinkButton(View view) {
        if (seconds <= 0) {
            textViewTavernResult.setText(R.string.tavern_loose);
            gameOn = false;
        } else if (countDrink < 10) {
            textViewTavernResult.setText(getTextForResult(countDrink));
            countDrink++;
        } else {
            gameOn = false;
            drunken = true;
                    if (seconds > 0) {
                        if (heroClass == 1) {
                    textViewTavernResult.setText(R.string.tavern_class_dependencies_mage);
                    youDie();
                } else if (heroRace == 1) {
                    textViewTavernResult.setText(R.string.tavern_race_dependencies_elf);
                } else if (heroRace == 2) {
                    textViewTavernResult.setText(R.string.tavern_race_dependencies_dwarf);
                } else {
                    textViewTavernResult.setText(R.string.tavern_win);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        text = textViewTavernResult.getText().toString();
        outState.putString("text", text);
        outState.putBoolean("blood", clothesInBlood);
        outState.putBoolean("treasures", treasures);
        outState.putBoolean("drunken", drunken);
        outState.putBoolean("princess", princess);
        outState.putBoolean("princessDead", princessDead);
        outState.putBoolean("triedToGetPrincess", triedToGetPrincess);
        outState.putInt("seconds", seconds);
        outState.putBoolean("gameOn", gameOn);
        outState.putInt("countDrink", countDrink);
        outState.putBoolean("gameWasOn", gameWasOn);
    }

    @Override
    protected void onPause() {
        gameWasOn = gameOn;
        gameOn = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameOn = gameWasOn;
    }

    public String getTextForResult(int i) {
        String[] results = getResources().getStringArray(R.array.drink_results);
        return results[i];
    }

    public void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String text = Integer.toString(seconds);
                textViewTimer.setText(text);
                if (gameOn) {
                    if (seconds > 0) {
                        seconds--;
                    } else {
                        textViewTavernResult.setText(R.string.tavern_loose);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void tryAgain(View view) {
        seconds = 30;
        gameOn = true;
        countDrink = 0;
        textViewTavernResult.setText("");
    }

    public void youDie() {
        Intent intentDie = new Intent(this, DieActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intentDie);
            }
        }, 3000);
    }

    public void returnFromTavern(View view) {
        Intent intentReturn = new Intent(this, StartingArea.class);
        intentReturn.putExtra("name", heroName);
        intentReturn.putExtra("race", heroRace);
        intentReturn.putExtra("class", heroClass);
        intentReturn.putExtra("blood", clothesInBlood);
        intentReturn.putExtra("treasures", treasures);
        intentReturn.putExtra("drunken", drunken);
        intentReturn.putExtra("princess", princess);
        intentReturn.putExtra("princessDead", princessDead);
        intentReturn.putExtra("triedToGetPrincess", triedToGetPrincess);
        startActivity(intentReturn);
    }
}