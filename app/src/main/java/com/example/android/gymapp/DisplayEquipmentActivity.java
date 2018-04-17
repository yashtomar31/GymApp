package com.example.android.gymapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.gymapp.Databases.DatabaseHelper;
import com.example.android.gymapp.Databases.Equipment;
import com.example.android.gymapp.Databases.EquipmentContract;

import java.util.ArrayList;

public class DisplayEquipmentActivity extends AppCompatActivity
{

    TextView addEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_equipment);


        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();


        addEquipment=(TextView) findViewById(R.id.add_equipment);


        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();

        String selectQuery="SELECT * FROM "+ EquipmentContract.EquipmentEntry.TABLE_NAME;

        Cursor cursor=database.rawQuery(selectQuery, null);

        String outputText="";
        outputText+="EquipmentID:Name:Tyope\n";

        if(cursor.moveToFirst())
        {
            do
            {
                Equipment addEquipment=new Equipment(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                Log.d("database", addEquipment.toString());
                outputText+=addEquipment.toString();
                outputText+="\n";
            }while(cursor.moveToNext());
        }


        Log.d("database", outputText);
        addEquipment.setText(outputText);

        database.close();
    }
}
