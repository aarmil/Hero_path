package com.demo.heropath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CityStreet extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private TextView textViewWalking;
    private int timesWalking = 0;
    private String text;
    private boolean clothesInBlood;
    private boolean treasures;
    private boolean drunken;
    private boolean princess;
    private boolean princessDead;
    private boolean triedToGetPrincess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_street);
        textViewWalking = findViewById(R.id.textViewWalking);
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

        if (savedInstanceState != null) {
            timesWalking = savedInstanceState.getInt("timesWalking");
            text = savedInstanceState.getString("textViewWalking");
            clothesInBlood = savedInstanceState.getBoolean("blood");
            treasures = savedInstanceState.getBoolean("treasures");
            drunken = savedInstanceState.getBoolean("drunken");
            princess = savedInstanceState.getBoolean("princess");
            princessDead = savedInstanceState.getBoolean("princessDead");
            triedToGetPrincess = savedInstanceState.getBoolean("triedToGetPrincess");
            textViewWalking.setText(text);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("timesWalking", timesWalking);
        outState.putString("textViewWalking", text);
        outState.putBoolean("blood", clothesInBlood);
        outState.putBoolean("treasures", treasures);
        outState.putBoolean("drunken", drunken);
        outState.putBoolean("princess", princess);
        outState.putBoolean("princessDead", princessDead);
        outState.putBoolean("triedToGetPrincess", triedToGetPrincess);
    }

    public void streetContinue(View view) {
        if (timesWalking == 0) {
            text = String.format("%s %s", getTextForWalking(0), getTextForLady(heroRace));
            textViewWalking.setText(text);
            timesWalking++;
        } else if (timesWalking == 1) {
            text = getTextForWalking(1);
            textViewWalking.setText(text);
            timesWalking++;
        } else if (timesWalking == 2) {
            text = String.format("%s %s", getTextForWalking(2), getTextForGang(heroClass));
            textViewWalking.setText(text);
            if (heroClass == 0) {
                clothesInBlood = true;
            }
            timesWalking++;
        } else if (timesWalking == 3) {
            text = getTextForWalking(3);
            textViewWalking.setText(text);
            timesWalking++;
        } else {
            text = getTextForWalking(4);
            textViewWalking.setText(text);
        }
    }

    public String getTextForWalking(int i) {
        String[] streetVariants = getResources().getStringArray(R.array.street_variants);
        return streetVariants[i];
    }

    public String getTextForLady(int i) {
        String[] raceDependenciesLady = getResources().getStringArray(R.array.race_dependencies_lady);
        return raceDependenciesLady[i];
    }

    public String getTextForGang(int i) {
        String[] classDependenciesGang = getResources().getStringArray(R.array.class_dependencies_gang);
        return classDependenciesGang[i];
    }

    public void streetReturn(View view) {
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