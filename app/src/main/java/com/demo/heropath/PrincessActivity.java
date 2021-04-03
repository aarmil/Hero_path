package com.demo.heropath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrincessActivity extends AppCompatActivity {

    private String heroName;
    private int heroRace;
    private int heroClass;
    private boolean clothesInBlood = false;
    private boolean treasures = false;
    private boolean drunken = false;
    private boolean princess = false;
    //each floor has its own description, array of strings linked to buttons and array of results.
    private int floor = 0;
    private TextView textViewFloorDescription;
    private TextView textViewPrincessResults;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    //index of button is the number of the element in choice array (for example, in choices_first_floor array).
    private int button1index = 0;
    private int button2index = 1;
    private int button3index = 2;
    private String text;
    private int countFirstButton = 0;
    private int countSecondButton = 0;
    private int countThirdButton = 0;
    private int heroPowerLevel = 0;
    private boolean artifact = false;
    private boolean attackBlocked = false;

    private boolean butlerIsKilled = false;
    private boolean magisterSecret = false;
    private boolean libraryDoorLocked = false;
    private boolean princessDead = false;
    private boolean magisterShieldDropped = false;
    private boolean duelWon = false;
    private boolean triedToGetPrincess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princess);
        Intent intent = getIntent();
        heroName = intent.getStringExtra("name");
        heroRace = intent.getIntExtra("race", 0);
        heroClass = intent.getIntExtra("class", 0);
        clothesInBlood = intent.getBooleanExtra("blood", false);
        treasures = intent.getBooleanExtra("treasures", false);
        drunken = intent.getBooleanExtra("drunken", false);
        princess = intent.getBooleanExtra("princess", false);
        textViewFloorDescription = findViewById(R.id.textViewFloorDescription);
        textViewPrincessResults = findViewById(R.id.textViewPrincessResults);
        textViewFloorDescription.setText(getFloorDescription(floor));
        buttonChoice1 = findViewById(R.id.buttonChoice1);
        buttonChoice2 = findViewById(R.id.buttonChoice2);
        buttonChoice3 = findViewById(R.id.buttonChoice3);
        buttonChoice1.setText(getFloorChoices(button1index));
        buttonChoice2.setText(getFloorChoices(button2index));
        buttonChoice3.setText(getFloorChoices(button3index));

        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
            clothesInBlood = savedInstanceState.getBoolean("blood");
            treasures = savedInstanceState.getBoolean("treasures");
            drunken = savedInstanceState.getBoolean("drunken");
            princess = savedInstanceState.getBoolean("princess");
            princessDead = savedInstanceState.getBoolean("princessDead");
            triedToGetPrincess = savedInstanceState.getBoolean("triedToGetPrincess");
            floor = savedInstanceState.getInt("floor");
            button1index = savedInstanceState.getInt("button1index");
            button2index = savedInstanceState.getInt("button2index");
            button3index = savedInstanceState.getInt("button3index");
            countFirstButton = savedInstanceState.getInt("countFirstButton");
            countSecondButton = savedInstanceState.getInt("countSecondButton");
            countThirdButton = savedInstanceState.getInt("countThirdButton");
            heroPowerLevel = savedInstanceState.getInt("heroPowerLevel");
            artifact = savedInstanceState.getBoolean("artifact");
            attackBlocked = savedInstanceState.getBoolean("attackBlocked");
            butlerIsKilled = savedInstanceState.getBoolean("butlerIsKilled");
            magisterSecret = savedInstanceState.getBoolean("magisterSecret");
            libraryDoorLocked = savedInstanceState.getBoolean("libraryDoorLocked");
            magisterShieldDropped = savedInstanceState.getBoolean("magisterShieldDropped");
            duelWon = savedInstanceState.getBoolean("duelWon");

            textViewPrincessResults.setText(text);
            setButtons(button1index, button2index, button3index);
        }
    }

    public String getFloorDescription(int i) {
        String[] floors = getResources().getStringArray(R.array.floor_description);
        return floors[i];
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        text = textViewPrincessResults.getText().toString();
        outState.putString("text", text);
        outState.putBoolean("blood", clothesInBlood);
        outState.putBoolean("treasures", treasures);
        outState.putBoolean("drunken", drunken);
        outState.putBoolean("princess", princess);
        outState.putBoolean("princessDead", princessDead);
        outState.putBoolean("triedToGetPrincess", triedToGetPrincess);
        outState.putInt("floor", floor);
        outState.putInt("button1index", button1index);
        outState.putInt("button2index", button2index);
        outState.putInt("button3index", button3index);
        outState.putInt("countFirstButton", countFirstButton);
        outState.putInt("countSecondButton", countSecondButton);
        outState.putInt("countThirdButton", countThirdButton);
        outState.putInt("heroPowerLevel", heroPowerLevel);
        outState.putBoolean("artifact", artifact);
        outState.putBoolean("attackBlocked", attackBlocked);
        outState.putBoolean("butlerIsKilled", butlerIsKilled);
        outState.putBoolean("magisterSecret", magisterSecret);
        outState.putBoolean("libraryDoorLocked", libraryDoorLocked);
        outState.putBoolean("magisterShieldDropped", magisterShieldDropped);
        outState.putBoolean("duelWon", duelWon);
    }

    //There is array of choices dedicated to each floor. It is general method to get strings for buttons depending on the floor
    public String getFloorChoices(int i) {
        if (floor == 0) {
            String[] choices = getResources().getStringArray(R.array.choices_first_floor);
            return choices[i];
        } else if (floor == 1) {
            String[] choices = getResources().getStringArray(R.array.choices_second_floor);
            return choices[i];
        } else if (floor == 2) {
            String[] choices = getResources().getStringArray(R.array.choices_third_floor);
            return choices[i];
        } else if (floor == 3) {
            String[] choices = getResources().getStringArray(R.array.choices_fourth_floor);
            return choices[i];
        } else if (floor == 4) {
            String[] choices = getResources().getStringArray(R.array.choices_fifth_floor);
            return choices[i];
        } else {
            return "";
        }
    }

    //It is general method to get strings for results of choices made depending on the floor
    public String getFloorResults(int i) {
        if (floor == 0) {
            String[] results = getResources().getStringArray(R.array.results_first_floor);
            return results[i];
        } else if (floor == 1) {
            String[] results = getResources().getStringArray(R.array.results_second_floor);
            return results[i];
        } else if (floor == 2) {
            String[] results = getResources().getStringArray(R.array.results_third_floor);
            return results[i];
        } else if (floor == 3) {
            String[] results = getResources().getStringArray(R.array.results_fourth_floor);
            return results[i];
        } else if (floor == 4) {
            String[] results = getResources().getStringArray(R.array.results_fifth_floor);
            return results[i];
        } else {
            return "";
        }
    }

    public void setFloorResults(int i) {
        textViewPrincessResults.setText(getFloorResults(i));
    }

    //complex method of getting results depending on using the first button. The result depends on the floor, id of button (different strings of the same button)
    //and on the hero parameters
    public void action1(View view) {
        //You're very hungry.Eat sneaks
        if (floor == 0) {
            setFloorResults(0);
            youDie();
            //Go swimming
        } else if (floor == 1) {
            setFloorResults(0);
            clothesInBlood = false;
        } else if (floor == 2) {
            //Button = Try to open golden door
            if (button1index == 0) {
                if (heroClass == 1) {
                    setFloorResults(2);
                } else {
                    setFloorResults(0);
                    if (heroClass == 0) {
                        button1index = 4;
                    } else if (heroClass == 2) {
                        button1index = 3;
                    }
                    buttonChoice1.setText(getFloorChoices(button1index));
                }
                //Button = Use lockpicks
            } else if (button1index == 3) {
                setFloorResults(3);
                button1index = 5;
                buttonChoice1.setText(getFloorChoices(button1index));
                //Button = Smash it
            } else if (button1index == 4) {
                if (drunken == false) {
                    setFloorResults(1);
                } else {
                    setFloorResults(3);
                    button1index = 5;
                    buttonChoice1.setText(getFloorChoices(button1index));
                }
                //Button = Take the amulet
            } else if (button1index == 5) {
                setFloorResults(4);
                heroPowerLevel++;
                drunken = false;
                button1index = 6;
                buttonChoice1.setText(getFloorChoices(button1index));
            }
        } else if (floor == 3) {
            //Button = Go to library
            if (button1index == 0 && !libraryDoorLocked) {
                if (heroClass == 0) {
                    if (countFirstButton == 0) {
                        setFloorResults(0);
                        countFirstButton++;
                    } else {
                        setFloorResults(1);
                    }
                } else {
                    if (countFirstButton == 0) {
                        setFloorResults(2);
                        magisterSecret = true;
                        if (heroClass == 1) {
                            button1index = 3;
                            buttonChoice1.setText(getFloorChoices(button1index));
                        } else {
                            if (heroClass == 2) {
                                countFirstButton++;
                            }
                        }
                    } else {
                        setFloorResults(4);
                    }
                }
                //Go deeper to the library
            } else if (button1index == 3 && !libraryDoorLocked) {
                setFloorResults(3);
                heroPowerLevel++;
                artifact = true;
                button1index = 0;
                buttonChoice1.setText(getFloorChoices(button1index));
                countFirstButton++;
            } else if (button1index == 6) {
                setButtons(0, 1, 2);
            } else if (button1index == 7 || button1index == 10 || button1index == 13) {
                attackBlocked = true;
                setFloorResults(11);
            } else {
                setFloorResults(18);
            }
        } else if (floor == 4) {
            if (button1index == 3) {
                princessReturn();
            } else if (button1index == 0) {
                if (drunken) {
                    setFloorResults(2);
                } else if (magisterSecret) {
                    setFloorResults(5);
                    setButtons(6, 7, 8);
                } else if (countFirstButton < 10) {
                    countFirstButton++;
                    setFloorResults(3);
                } else if (countFirstButton == 10) {
                    setFloorResults(4);
                    setButtons(6, 7, 8);
                }
            } else if (button1index == 10) {
                if (!artifact) {
                    setFloorResults(11);
                } else if (heroClass == 1 && artifact) {
                    setButtons(3, 4, 9);
                    setFloorResults(8);
                    princessDead = true;
                } else if (!magisterShieldDropped) {
                    if (heroPowerLevel >= 2 && artifact) {
                        setButtons(6, 7, 8);
                        duelWon = true;
                        if (heroClass == 0) {
                            setFloorResults(12);
                        } else if (heroClass == 2) {
                            setFloorResults(13);
                        }
                    } else {
                        if (heroClass == 0) {
                            setFloorResults(17);
                        } else if (heroClass == 2) {
                            setFloorResults(18);
                        }
                        youDie();
                    }
                } else {
                    setButtons(6, 7, 8);
                    duelWon = true;
                    if (heroClass == 0) {
                        setFloorResults(20);
                    } else if (heroClass == 2) {
                        setFloorResults(21);
                    }
                }
            } else if (button1index == 6) {
                if (drunken) {
                    setButtons(3, 4, 9);
                    setFloorResults(27);
                } else if (clothesInBlood) {
                    setButtons(3, 4, 9);
                    setFloorResults(28);
                } else if (treasures) {
                    setButtons(13, 13, 13);
                    setFloorResults(30);
                    princess = true;
                } else {
                    setButtons(3, 4, 5);
                    setFloorResults(31);
                }
            } else if (button3index == 13) {
                princessReturn();
            }
        }
    }

    public void action2(View view) {
        if (floor == 0) {
            if (butlerIsKilled == false) {
                butlerIsKilled = true;
                button3index = 2;
                buttonChoice3.setText(getFloorChoices(button3index));
                if (heroClass == 0) {
                    setFloorResults(1);
                    clothesInBlood = true;
                } else if (heroClass == 1) {
                    setFloorResults(2);
                } else if (heroClass == 2) {
                    setFloorResults(3);
                    clothesInBlood = true;
                }
            } else {
                setFloorResults(4);
            }
        } else if (floor == 1) {
            if ((button2index == 1) && (!artifact)) {
                if (heroClass == 0) {
                    setFloorResults(1);
                    button2index = 3;
                    buttonChoice2.setText(getFloorChoices(button2index));
                } else if (heroClass == 1) {
                    setFloorResults(2);
                    youDie();
                } else if (heroClass == 2) {
                    setFloorResults(3);
                    button2index = 4;
                    buttonChoice2.setText(getFloorChoices(button2index));
                }
            } else if (button2index == 3) {
                setFloorResults(4);
                heroPowerLevel++;
                artifact = true;
                button2index = 1;
                buttonChoice2.setText(getFloorChoices(button2index));
            } else if (button2index == 4) {
                setFloorResults(5);
                heroPowerLevel++;
                artifact = true;
                button2index = 1;
                buttonChoice2.setText(getFloorChoices(button2index));
            } else {
                setFloorResults(6);
            }
        } else if (floor == 2) {
            //Check who is screaming
            if (button2index == 1) {
                setFloorResults(5);
                if (heroClass == 0) {
                    button2index = 7;
                } else if (heroClass == 1) {
                    button2index = 8;
                } else if (heroClass == 2) {
                    button2index = 9;
                }
                buttonChoice2.setText(getFloorChoices(button2index));
                //Use whirlwind attack
            } else if (button2index == 7) {
                if (heroPowerLevel == 0) {
                    setFloorResults(6);
                    youDie();
                } else {
                    setFloorResults(7);
                    button2index = 10;
                    buttonChoice2.setText(getFloorChoices(button2index));
                }
                //Fireball
            } else if (button2index == 8) {
                setFloorResults(8);
                button2index = 10;
                buttonChoice2.setText(getFloorChoices(button2index));
                //Lock the door
            } else if (button2index == 9) {
                setFloorResults(9);
                button2index = 10;
                buttonChoice2.setText(getFloorChoices(button2index));
            }
        } else if (floor == 3) {
            if (button2index == 1) {
                if (countSecondButton == 0) {
                    setFloorResults(5);
                    setButtons(6, 4, 5);
                } else {
                    setFloorResults(8);
                }
            } else if (button2index == 4) {
                setFloorResults(7);
                heroPowerLevel++;
                countSecondButton++;
                setButtons(0, 1, 2);
            } else if (button2index == 8 || button2index == 11 || button2index == 14) {
                if (!attackBlocked) {
                    setFloorResults(10);
                    youDie();
                } else {
                    if (button2index == 8) {
                        setFloorResults(12);
                    } else if (button2index == 11) {
                        setFloorResults(14);
                    } else if (button2index == 14) {
                        setFloorResults(16);
                    }
                    setButtons(0, 1, 2);
                }
            }
        } else if (floor == 4) {
            if (button2index == 4) {
                princessReturn();
            } else if (button2index == 1) {
                if (heroClass == 1 && artifact) {
                    setButtons(3, 4, 9);
                    setFloorResults(8);
                    princessDead = true;
                } else if (heroPowerLevel == 3 && artifact) {
                    setButtons(3, 4, 9);
                    princessDead = true;
                    if (heroClass == 0) {
                        setFloorResults(6);
                    } else if (heroClass == 2) {
                        setFloorResults(7);
                    }
                } else {
                    setFloorResults(9);
                    setButtons(10, 11, 12);
                }
            } else if (button2index == 11) {
                if (!magisterShieldDropped) {
                    magisterShieldDropped = true;
                    if (heroClass == 0) {
                        setFloorResults(15);
                    } else if (heroClass == 1) {
                        setFloorResults(14);
                    } else {
                        setFloorResults(16);
                    }
                } else {
                    setFloorResults(19);
                    youDie();
                }
            } else if (button2index == 7) {
                if (drunken) {
                    setButtons(3, 4, 9);
                    setFloorResults(27);
                } else if (clothesInBlood) {
                    setButtons(3, 4, 9);
                    setFloorResults(28);
                } else if (duelWon) {
                    countSecondButton++;
                    if (countSecondButton < 3) {
                        setFloorResults(32);
                    } else {
                        setFloorResults(34);
                    }
                } else {
                    setFloorResults(33);
                }
            } else if (button3index == 13) {
                princessReturn();
            }
        }
    }

    public void action3(View view) {
        if (floor == 0) {
            if (button3index == 2) {
                if (butlerIsKilled) {
                    goNextFloor();
                } else {
                    if (countThirdButton == 0) {
                        setFloorResults(5);
                        button3index = 3;
                        buttonChoice3.setText(getFloorChoices(button3index));
                        countThirdButton++;
                    } else if (countThirdButton == 1) {
                        setFloorResults(6);
                        countThirdButton++;
                    } else {
                        goNextFloor();
                    }
                }
            } else if (button3index == 3) {
                button3index = 2;
                buttonChoice3.setText(getFloorChoices(button3index));
                if (drunken) {
                    setFloorResults(7);
                } else if (clothesInBlood) {
                    setFloorResults(8);
                } else if (treasures) {
                    setFloorResults(9);
                } else {
                    setFloorResults(10);
                }
            }
        } else if (floor == 1) {
            goNextFloor();
        } else if (floor == 2) {
            goNextFloor();
        } else if (floor == 3) {
            if (button3index == 5) {
                setFloorResults(6);
                drunken = true;
                setButtons(0, 1, 2);
                countSecondButton++;
            } else if (button3index == 2) {
                if (countThirdButton == 0) {
                    countThirdButton++;
                    setFloorResults(9);
                    if (heroClass == 0) {
                        setButtons(7, 8, 9);
                    } else if (heroClass == 1) {
                        setButtons(10, 11, 12);
                    } else if (heroClass == 2) {
                        setButtons(13, 14, 15);
                    }
                } else {
                    goNextFloor();
                }
            } else if (button3index == 9 || button3index == 12 || button3index == 15) {
                if (!attackBlocked) {
                    setFloorResults(10);
                    youDie();
                } else {
                    if (button3index == 9) {
                        setFloorResults(13);
                        clothesInBlood = true;
                    } else if (button3index == 12) {
                        setFloorResults(15);
                    } else if (button3index == 15) {
                        setFloorResults(17);
                        libraryDoorLocked = true;
                    }
                    setButtons(0, 1, 2);
                }
            }
        } else if (floor == 4) {
            if (button3index == 5 || button3index == 9) {
                princessReturn();
            } else if (button3index == 2) {
                setFloorResults(10);
                setButtons(10, 11, 12);
            } else if (button3index == 12) {
                if (!magisterShieldDropped) {
                    if (heroClass == 0) {
                        setFloorResults(22);
                    } else {
                        setFloorResults(23);
                    }
                    youDie();
                } else {
                    duelWon = true;
                    setButtons(6, 7, 8);
                    if (heroClass == 0) {
                        setFloorResults(24);
                    } else if (heroClass == 1) {
                        setFloorResults(25);
                    } else {
                        setFloorResults(26);
                    }
                }
            } else if (button3index == 8) {
                setFloorResults(29);
                setButtons(13, 13, 13);
            } else if (button3index == 13) {
                princessReturn();
            }
        }
    }

    private void goNextFloor() {
        floor++;
        textViewFloorDescription.setText(getFloorDescription(floor));
        countFirstButton = 0;
        countSecondButton = 0;
        countThirdButton = 0;
        if (floor == 4 && (heroRace == 2 || clothesInBlood)) {
            if (heroRace == 2) {
                setButtons(3, 4, 5);
                setFloorResults(0);
            } else if (clothesInBlood) {
                setFloorResults(1);
                setButtons(10, 11, 12);
            }
        } else {
            setButtons(0, 1, 2);
            textViewPrincessResults.setText("");
        }
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

    public void princessReturn() {
        triedToGetPrincess = true;
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

    public void setButtons(int i1, int i2, int i3) {
        button1index = i1;
        button2index = i2;
        button3index = i3;
        buttonChoice1.setText(getFloorChoices(button1index));
        buttonChoice2.setText(getFloorChoices(button2index));
        buttonChoice3.setText(getFloorChoices(button3index));
    }
}