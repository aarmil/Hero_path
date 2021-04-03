package com.demo.heropath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class Dungeon extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private boolean clothesInBlood;
    private TextView textViewDungeonResult;
    private TextView textViewCorridors;
    //Position: 0 - hero looks north, 1 - east, 2 - south, 3 - west
    private int position = 0;
    private int dungeonRoom = 0;
    private boolean treasures = false;
    private boolean drunken;
    private boolean princess;
    private boolean princessDead;
    private boolean triedToGetPrincess;
    private String text;
    private String text2;
    int seconds;
    Button buttonForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        textViewDungeonResult = findViewById(R.id.textViewDungeonResult);
        buttonForward = findViewById(R.id.buttonForward);
        textViewCorridors = findViewById(R.id.textViewCorridors);
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
            text = savedInstanceState.getString("text");
            position = savedInstanceState.getInt("position");
            dungeonRoom = savedInstanceState.getInt("dungeonRoom");
            clothesInBlood = savedInstanceState.getBoolean("blood");
            treasures = savedInstanceState.getBoolean("treasures");
            drunken = savedInstanceState.getBoolean("drunken");
            princess = savedInstanceState.getBoolean("princess");
            princessDead = savedInstanceState.getBoolean("princessDead");
            triedToGetPrincess = savedInstanceState.getBoolean("triedToGetPrincess");
            textViewDungeonResult.setText(text);
        }
        defineTextForCorridors();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", text);
        outState.putString("text2", text2);
        outState.putInt("position", position);
        outState.putInt("dungeonRoom", dungeonRoom);
        outState.putBoolean("blood", clothesInBlood);
        outState.putBoolean("treasures", treasures);
        outState.putBoolean("drunken", drunken);
        outState.putBoolean("princess", princess);
        outState.putBoolean("princessDead", princessDead);
        outState.putBoolean("triedToGetPrincess", triedToGetPrincess);
    }

    public void moveForward(View view) {
        if (position == 0) {
            switch (dungeonRoom) {
                case 0:
                case 1:
                case 4:
                case 6:
                case 7:
                case 8:
                    deadEnd();
                    break;
                case 2:
                    enterThirdRoom();
                    break;
                case 3:
                    enterFifthRoom();
                    break;
                case 5:
                    enterFinalRoom();
                    break;
            }
        } else if (position == 1) {
            switch (dungeonRoom) {
                case 0:
                    enterFirstRoom();
                    break;
                case 1:
                case 4:
                case 7:
                case 8:
                    deadEnd();
                    break;
                case 2:
                    enterZeroRoom();
                    break;
                case 3:
                    enterFourthRoom();
                    break;
                case 5:
                    enterSeventhRoom();
                    break;
                case 6:
                    enterFifthRoom();
                    break;
            }
        } else if (position == 2) {
            switch (dungeonRoom) {
                case 0:
                    dungeonReturn();
                    break;
                case 1:
                case 2:
                case 4:
                case 6:
                case 7:
                    deadEnd();
                    break;
                case 3:
                    enterSecondRoom();
                    break;
                case 5:
                    enterThirdRoom();
                    break;
                case 8:
                    enterFifthRoom();
                    break;
            }
        } else if (position == 3) {
            switch (dungeonRoom) {
                case 0:
                    enterSecondRoom();
                    break;
                case 1:
                    enterZeroRoom();
                    break;
                case 2:
                case 3:
                case 6:
                case 8:
                    deadEnd();
                    break;
                case 4:
                    enterThirdRoom();
                    break;
                case 5:
                    enterSixthRoom();
                    break;
                case 7:
                    enterFifthRoom();
                    break;
            }
        }
        defineTextForCorridors();
    }


    public void moveLeft(View view) {
        if (position == 0) {
            position = 3;
            buttonForward.performClick();
        } else if (position == 1) {
            position = 0;
            buttonForward.performClick();
        } else if (position == 2) {
            position = 1;
            buttonForward.performClick();
        } else if (position == 3) {
            position = 2;
            buttonForward.performClick();
        }
        defineTextForCorridors();
    }

    public void moveBack(View view) {
        if (position == 0) {
            position = 2;
            buttonForward.performClick();
        } else if (position == 1) {
            position = 3;
            buttonForward.performClick();
        } else if (position == 2) {
            position = 0;
            buttonForward.performClick();
        } else if (position == 3) {
            position = 1;
            buttonForward.performClick();
        }
        defineTextForCorridors();
    }

    public void moveRight(View view) {
        if (position == 0) {
            position = 1;
            buttonForward.performClick();
        } else if (position == 1) {
            position = 2;
            buttonForward.performClick();
        } else if (position == 2) {
            position = 3;
            buttonForward.performClick();
        } else if (position == 3) {
            position = 0;
            buttonForward.performClick();
        }
        defineTextForCorridors();
    }

    public void defineTextForCorridors() {
        if (position == 0) {
            switch (dungeonRoom) {
                case 0:
                    setTextForCorridors(12);
                    break;
                case 1:
                case 4:
                case 7:
                    setTextForCorridors(0);
                    break;
                case 6:
                    setTextForCorridors(1);
                    break;
                case 8:
                    setTextForCorridors(2);
                    break;
                case 2:
                    setTextForCorridors(5);
                    break;
                case 3:
                    setTextForCorridors(9);
                    break;
                case 5:
                    setTextForCorridors(13);
                    break;
            }
        } else if (position == 1) {
            switch (dungeonRoom) {
                case 0:
                    setTextForCorridors(9);
                    break;
                case 1:
                case 4:
                case 7:
                    setTextForCorridors(2);
                    break;
                case 8:
                    setTextForCorridors(1);
                    break;
                case 2:
                    setTextForCorridors(7);
                    break;
                case 3:
                    setTextForCorridors(11);
                    break;
                case 5:
                    setTextForCorridors(13);
                    break;
                case 6:
                    setTextForCorridors(3);
                    break;
            }
        } else if (position == 2) {
            switch (dungeonRoom) {
                case 0:
                    setTextForCorridors(11);
                    break;
                case 1:
                case 4:
                case 7:
                    setTextForCorridors(1);
                    break;
                case 2:
                    setTextForCorridors(8);
                    break;
                case 6:
                    setTextForCorridors(0);
                    break;
                case 3:
                    setTextForCorridors(10);
                    break;
                case 5:
                    setTextForCorridors(13);
                    break;
                case 8:
                    setTextForCorridors(3);
                    break;
            }
        } else if (position == 3) {
            switch (dungeonRoom) {
                case 0:
                    setTextForCorridors(10);
                    break;
                case 1:
                case 4:
                case 7:
                    setTextForCorridors(3);
                    break;
                case 2:
                    setTextForCorridors(14);
                    break;
                case 3:
                    setTextForCorridors(12);
                    break;
                case 6:
                    setTextForCorridors(2);
                    break;
                case 8:
                    setTextForCorridors(0);
                    break;
                case 5:
                    setTextForCorridors(13);
                    break;
            }
        }
    }

    public void deadEnd() {
        text = getResources().getString(R.string.no_corridor);
        textViewDungeonResult.setText(text);
    }

    public String getTextForRoom(int i) {
        String[] roomVariants = getResources().getStringArray(R.array.dungeon_rooms);
        return roomVariants[i];
    }

    public String getTextForMonstersRoom(int i) {
        if (heroRace == 2) {
            return getResources().getString(R.string.dwarf_monsters);
        } else {
            String[] roomMonsters = getResources().getStringArray(R.array.class_dependencies_monsters);
            return roomMonsters[i];
        }
    }

    public String getTextForTrapRoom(int i) {
        if (heroRace == 2) {
            return getResources().getString(R.string.dwarf_traps);
        } else {
            String[] roomTraps = getResources().getStringArray(R.array.class_dependencies_trap);
            return roomTraps[i];
        }
    }

    public String getTextForDog(int i) {
        String[] roomDog = getResources().getStringArray(R.array.race_dependencies_dog);
        return roomDog[i];
    }

    public String getTextForSuccubus(int i) {
        String[] roomSuccubus = getResources().getStringArray(R.array.race_dependencies_succubus);
        return roomSuccubus[i];
    }

    public void setTextForCorridors(int i) {
        String[] roomCorridors = getResources().getStringArray(R.array.corridors);
        text2 = roomCorridors[i];
        textViewCorridors.setText(text2);
    }

    public void enterZeroRoom() {
        dungeonRoom = 0;
        text = getTextForRoom(dungeonRoom);
        textViewDungeonResult.setText(text);
    }

    public void enterFirstRoom() {
        dungeonRoom = 1;
        text = String.format("%s %s", getTextForRoom(dungeonRoom), getTextForMonstersRoom(heroClass));
        textViewDungeonResult.setText(text);
        if (heroClass == 0) {
            clothesInBlood = true;
        }
    }

    public void enterSecondRoom() {
        dungeonRoom = 2;
        text = getTextForRoom(dungeonRoom);
        textViewDungeonResult.setText(text);
    }

    public void enterThirdRoom() {
        dungeonRoom = 3;
        text = String.format("%s %s", getTextForRoom(dungeonRoom), getTextForTrapRoom(heroClass));
        textViewDungeonResult.setText(text);
    }

    public void enterFourthRoom() {
        dungeonRoom = 4;
        text = String.format("%s %s", getTextForRoom(dungeonRoom), getTextForDog(heroRace));
        textViewDungeonResult.setText(text);
    }

    public void enterFifthRoom() {
        dungeonRoom = 5;
        text = getTextForRoom(dungeonRoom);
        textViewDungeonResult.setText(text);
    }

    public void enterSixthRoom() {
        dungeonRoom = 6;
        text = String.format("%s %s", getTextForRoom(dungeonRoom), getTextForSuccubus(heroRace));
        textViewDungeonResult.setText(text);
        if (heroRace == 0) {
            youDie();
        }
    }

    public void enterSeventhRoom() {
        dungeonRoom = 7;
        text = getTextForRoom(dungeonRoom);
        textViewDungeonResult.setText(text);
        youDie();
    }

    public void enterFinalRoom() {
        dungeonRoom = 8;
        text = getTextForRoom(dungeonRoom);
        textViewDungeonResult.setText(text);
        treasures = true;
    }

    public void youDie() {
        seconds = 0;
        Intent intentDie = new Intent(this, DieActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intentDie);
            }
        }, 4000);
    }


    public void dungeonReturn(View view) {
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

    public void dungeonReturn() {
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