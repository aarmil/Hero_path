package com.demo.heropath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRetire extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private boolean treasures = false;
    private boolean drunken = false;
    private boolean princess = false;
    private boolean princessDead = false;
    private boolean triedToGetPrincess = false;
    private String text = "";
    private TextView textViewRetire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retire);
        textViewRetire = findViewById(R.id.textViewRetire);
        Intent intent = getIntent();
        heroName = intent.getStringExtra("name");
        heroRace = intent.getIntExtra("race", 0);
        heroClass = intent.getIntExtra("class", 0);
        treasures = intent.getBooleanExtra("treasures", false);
        drunken = intent.getBooleanExtra("drunken", false);
        princess = intent.getBooleanExtra("princess", false);
        princessDead = intent.getBooleanExtra("princessDead", false);
        setRetireDescription();
    }

    public void setRetireDescription() {
        if (heroRace == 0 || heroRace == 1) {
            if (drunken) {
                text += getTextRetire(5) + " " + heroName + " " + getTextRetire(6);
                if (treasures) {
                    if (heroClass == 1) {
                        text += "\n" + getTextRetire(9);
                        if (princessDead) {
                            text += "\n" + getTextRetire(14);
                            text += "\n" + getTextRetire(15);
                        }
                    } else {
                        text += "\n" + getTextRetire(8);
                    }
                } else {
                    text += "\n" + getTextRetire(7);
                }
            } else {
                text += getTextRetire(0);
                if (treasures) {
                    if (princess) {
                        text += "\n" + getTextRetire(13);
                    } else {
                        if (heroClass == 0) {
                            text += "\n" + getTextRetire(10);
                        } else if (heroClass == 1) {
                            text += "\n" + getTextRetire(11);
                        } else {
                            text += "\n" + getTextRetire(12);
                        }
                    }
                } else {
                    text += "\n" + getTextRetire(1);
                    if (heroClass == 0) {
                        text += "\n" + getTextRetire(2);
                    } else if (heroClass == 1) {
                        text += "\n" + getTextRetire(3);
                    } else {
                        text += "\n" + getTextRetire(4);
                    }
                }
                if (princessDead) {
                    text += "\n" + getTextRetire(14);
                    if (heroClass == 1) {
                        text += "\n" + getTextRetire(15);
                    }
                }
            }
        } else {
            text += getTextRetire(0);
            if (drunken) {
                text += "\n" + getTextRetire(6) + heroName + " " + getTextRetire(7);
                if (treasures) {
                    text += "\n" + getTextRetire(9);
                } else {
                    text += "\n" + getTextRetire(8);
                }
            } else {
                if (treasures) {
                    text += "\n" + getTextRetire(10);
                } else {
                    text += "\n" + getTextRetire(1);
                    if (heroClass == 0) {
                        text += "\n" + getTextRetire(2);
                    } else if (heroClass == 1) {
                        text += "\n" + getTextRetire(3);
                    } else {
                        text += "\n" + getTextRetire(4);
                    }
                    text += "\n" + getTextRetire(5);
                }
                if (princessDead) {
                    text += "\n" + getTextRetire(14);
                    if (heroClass == 1) {
                        text += "\n" + getTextRetire(15);
                    }
                }
            }
        }
        textViewRetire.setText(text);
    }

    public String getTextRetire(int i) {
        String[] textsForRetire;
        if (heroRace == 0) {
            textsForRetire = getResources().getStringArray(R.array.retirement_results_human);
        } else if (heroRace == 1) {
            textsForRetire = getResources().getStringArray(R.array.retirement_results_elf);
        } else {
            textsForRetire = getResources().getStringArray(R.array.retirement_results_dwarf);
        }
        return textsForRetire[i];
    }
}