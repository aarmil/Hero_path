package com.demo.heropath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die);
        Intent intent = getIntent();
    }

    public void startAgain(View view) {
        Intent intentAgain = new Intent(this, MainActivity.class);
        startActivity(intentAgain);
    }
}