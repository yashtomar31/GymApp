package com.example.android.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayTrainerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trainer);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

    }
}
