package com.demo.heropath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingArea extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private boolean clothesInBlood = false;
    private boolean treasures = false;
    private boolean drunken = false;
    private boolean princess = false;
    private boolean princessDead;
    private boolean triedToGetPrincess;
    private TextView textHeroDescription;
    private Button buttonPrincess;
    private Button buttonDungeon;
    private Button buttonTavern;
    int seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_area);
        textHeroDescription = findViewById(R.id.textHeroDescription);
        buttonPrincess = findViewById(R.id.buttonPrincess);
        buttonDungeon = findViewById(R.id.buttonDungeon);
        buttonTavern = findViewById(R.id.buttonTavern);
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
        setHeroDescription();
    }

    public void setHeroDescription() {
        String fullDescription = String.format("Your name is %s. %s %s", heroName, getDescriptionOfRaceByPosition(heroRace),
                getDescriptionOfClassByPosition(heroClass));
        if (clothesInBlood == true) {
            fullDescription += " " + getResources().getText(R.string.clothes_in_blood);
        }
        if (treasures == true) {
            fullDescription += " " + getResources().getText(R.string.have_treasures);
        }
        if (drunken == true) {
            fullDescription += " " + getResources().getText(R.string.hero_drunk);
        }
        if (princess == true) {
            fullDescription += " " + getResources().getText(R.string.hero_princess);
        }
        textHeroDescription.setText(fullDescription);
    }

    public String getDescriptionOfRaceByPosition(int position) {
        String[] descriptions = getResources().getStringArray(R.array.race_description);
        return descriptions[position];
    }

    public String getDescriptionOfClassByPosition(int position) {
        String[] descriptions = getResources().getStringArray(R.array.class_description);
        return descriptions[position];
    }

    public void goStreets(View view) {
        Intent intentStreets = new Intent(this, CityStreet.class);
        intentStreets.putExtra("name", heroName);
        intentStreets.putExtra("race", heroRace);
        intentStreets.putExtra("class", heroClass);
        intentStreets.putExtra("blood", clothesInBlood);
        intentStreets.putExtra("treasures", treasures);
        intentStreets.putExtra("drunken", drunken);
        intentStreets.putExtra("princess", princess);
        intentStreets.putExtra("princessDead", princessDead);
        intentStreets.putExtra("triedToGetPrincess", triedToGetPrincess);
        startActivity(intentStreets);
    }

    public void goDungeon(View view) {
        if (treasures) {
            buttonDungeon.setText(R.string.treasure_is_yours_button);
        } else {
            Intent intentDungeon = new Intent(this, Dungeon.class);
            intentDungeon.putExtra("name", heroName);
            intentDungeon.putExtra("race", heroRace);
            intentDungeon.putExtra("class", heroClass);
            intentDungeon.putExtra("blood", clothesInBlood);
            intentDungeon.putExtra("treasures", treasures);
            intentDungeon.putExtra("drunken", drunken);
            intentDungeon.putExtra("princess", princess);
            intentDungeon.putExtra("princessDead", princessDead);
            intentDungeon.putExtra("triedToGetPrincess", triedToGetPrincess);
            startActivity(intentDungeon);
        }
    }

    public void goTavern(View view) {
        if (!princess) {
            Intent intentTavern = new Intent(this, TavernActivity.class);
            intentTavern.putExtra("name", heroName);
            intentTavern.putExtra("race", heroRace);
            intentTavern.putExtra("class", heroClass);
            intentTavern.putExtra("blood", clothesInBlood);
            intentTavern.putExtra("treasures", treasures);
            intentTavern.putExtra("drunken", drunken);
            intentTavern.putExtra("princess", princess);
            intentTavern.putExtra("princessDead", princessDead);
            intentTavern.putExtra("triedToGetPrincess", triedToGetPrincess);
            startActivity(intentTavern);
        } else {
            buttonTavern.setText(R.string.princess_forbid);
        }
    }

    public void goPrincess(View view) {
        if (princessDead) {
            buttonPrincess.setText(R.string.princess_dead_for_button);
        } else if (princess) {
            buttonPrincess.setText(R.string.princess_is_yours_button);
        } else if (triedToGetPrincess) {
            buttonPrincess.setText(R.string.princess_refused_you_button);
        } else {
            Intent intentPrincess = new Intent(this, PrincessActivity.class);
            intentPrincess.putExtra("name", heroName);
            intentPrincess.putExtra("race", heroRace);
            intentPrincess.putExtra("class", heroClass);
            intentPrincess.putExtra("blood", clothesInBlood);
            intentPrincess.putExtra("treasures", treasures);
            intentPrincess.putExtra("drunken", drunken);
            intentPrincess.putExtra("princess", princess);
            intentPrincess.putExtra("princessDead", princessDead);
            intentPrincess.putExtra("triedToGetPrincess", triedToGetPrincess);
            startActivity(intentPrincess);
        }
    }

    public void goRetire(View view) {
        Intent intentRetire = new Intent(this, ActivityRetire.class);
        intentRetire.putExtra("name", heroName);
        intentRetire.putExtra("race", heroRace);
        intentRetire.putExtra("class", heroClass);
        intentRetire.putExtra("treasures", treasures);
        intentRetire.putExtra("drunken", drunken);
        intentRetire.putExtra("princess", princess);
        intentRetire.putExtra("princessDead", princessDead);
        intentRetire.putExtra("triedToGetPrincess", triedToGetPrincess);
        startActivity(intentRetire);
    }
}
