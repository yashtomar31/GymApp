package com.example.android.gymapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.widget.TextView;

import com.example.android.gymapp.Databases.Customer;
import com.example.android.gymapp.Databases.CustomerContract;
import com.example.android.gymapp.Databases.DatabaseHelper;
import com.example.android.gymapp.Databases.Trainer;
import com.example.android.gymapp.Databases.TrainerContract;

import java.util.ArrayList;

public class DisplayTrainerActivity extends AppCompatActivity
{

    TextView addTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trainer);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();


        addTrainer=(TextView) findViewById(R.id.add_trainer);

        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();

        String selectQuery="SELECT * FROM "+ TrainerContract.TrainerEntry.TABLE_NAME;

        Cursor cursor=database.rawQuery(selectQuery, null);

        String outputText="";
        outputText+="TrainerID:Name:Level:Age:Email:Phone\n";

        if(cursor.moveToFirst())
        {
            do
            {
                Trainer addTrainer=new Trainer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4));
                Log.d("database", addTrainer.toString());
                outputText+=addTrainer.toString();
                outputText+="\n";
            }while(cursor.moveToNext());
        }

        Log.d("database", outputText);
        addTrainer.setText(outputText);

        database.close();


    }
}
