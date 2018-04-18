package com.example.android.gymapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gymapp.Databases.CustomerContract;
import com.example.android.gymapp.Databases.DatabaseHelper;
import com.example.android.gymapp.Databases.Trainer;
import com.example.android.gymapp.Databases.TrainerContract;

public class SelectTrainerActivity extends AppCompatActivity
{

    EditText selectTrainer;
    TextView textViewTrainer;
    Button selectedTrainer;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trainer);

        Log.d("database", "THUGGGGGGG");

        selectTrainer=(EditText) findViewById(R.id.edit_text_set_trainer);
        textViewTrainer=(TextView) findViewById(R.id.text_set_trainer);
        selectedTrainer=(Button) findViewById(R.id.button_set_trainer);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database=databaseHelper.getWritableDatabase();


        String selectQuery="SELECT * FROM "+ TrainerContract.TrainerEntry.TABLE_NAME;

        Cursor cursor=database.rawQuery(selectQuery, null);

        int c=0;

        if(cursor.moveToFirst())
        {
            do
            {
                //Trainer addTrainer=new Trainer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4));
                c++;
                // Log.d("database", addTrainer.toString());
            }while(cursor.moveToNext());
        }


        textViewTrainer.setText("Available Trainers: 1 to"+String.valueOf(c));


        selectedTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
                SQLiteDatabase database=databaseHelper.getWritableDatabase();


                String selectQuery="SELECT * FROM "+ TrainerContract.TrainerEntry.TABLE_NAME;

                Cursor cursor=database.rawQuery(selectQuery, null);

                int c=0;

                if(cursor.moveToFirst())
                {
                    do
                    {
                        //Trainer addTrainer=new Trainer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4));
                        c++;
                        // Log.d("database", addTrainer.toString());
                    }while(cursor.moveToNext());
                }


                textViewTrainer.setText("Available Trainers: 1 to"+String.valueOf(c));


                int ch=0;

                ch=Integer.parseInt(selectTrainer.getText().toString());

                ContentValues cv=new ContentValues();

                cv.put(CustomerContract.CustomerEntry.COLUMN_TRAINERID, ch);
                database.update(CustomerContract.CustomerEntry.TABLE_NAME, cv, CustomerContract.CustomerEntry.COLUMN_CUSTOMERID+"="+String.valueOf(DataActivity.currentID), null);

                database.close();

                finish();

            }
        });
    }
}
