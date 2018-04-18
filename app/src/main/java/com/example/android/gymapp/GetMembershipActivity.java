package com.example.android.gymapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.gymapp.Databases.CustomerContract;
import com.example.android.gymapp.Databases.DatabaseHelper;
import com.example.android.gymapp.Databases.Equipment;
import com.example.android.gymapp.Databases.EquipmentCheckoutRelationContract;
import com.example.android.gymapp.Databases.MembershipContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetMembershipActivity extends AppCompatActivity
{

    Button member1;
    Button member2;
    Button member3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_membership);


        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        final SQLiteDatabase database=databaseHelper.getWritableDatabase();
        final ContentValues cv=new ContentValues();
        member1=(Button) findViewById(R.id.btn_get_membership1);
        member2=(Button) findViewById(R.id.btn_get_membership2);
        member3=(Button) findViewById(R.id.btn_get_membership3);

        member1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Selected 1 year @ 1000Rs plan", Toast.LENGTH_SHORT).show();
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPLENGTH, "1 year");
                cv.put(MembershipContract.MembershipEntry.COLUMN_CUSTOMERID, DataActivity.currentID);
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPRATE, "1000 Rs");
                cv.put(MembershipContract.MembershipEntry.COLUMN_DATECREATED, getDateCreated());
                cv.put(MembershipContract.MembershipEntry.COLUMN_EXPIRYDATE, getExpiryDate1());

                long id=database.insert(MembershipContract.MembershipEntry.TABLE_NAME, null, cv);
                Log.d("database","Current user id: "+String.valueOf(id));

                finish();
            }
        });

        member2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Selected 2 years @ 1500Rs plan", Toast.LENGTH_SHORT).show();
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPLENGTH, "2 years");
                cv.put(MembershipContract.MembershipEntry.COLUMN_CUSTOMERID, DataActivity.currentID);
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPRATE, "1500 Rs");
                cv.put(MembershipContract.MembershipEntry.COLUMN_DATECREATED, getDateCreated());
                cv.put(MembershipContract.MembershipEntry.COLUMN_EXPIRYDATE, getExpiryDate2());

                long id=database.insert(MembershipContract.MembershipEntry.TABLE_NAME, null, cv);
                Log.d("database","Current user id: "+String.valueOf(id));

                finish();
            }
        });

        member3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Selected 3 years @ 2500Rs plan", Toast.LENGTH_SHORT).show();
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPLENGTH, "3 years");
                cv.put(MembershipContract.MembershipEntry.COLUMN_CUSTOMERID, DataActivity.currentID);
                cv.put(MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPRATE, "2500 Rs");
                cv.put(MembershipContract.MembershipEntry.COLUMN_DATECREATED, getDateCreated());
                cv.put(MembershipContract.MembershipEntry.COLUMN_EXPIRYDATE, getExpiryDate3());

                long id=database.insert(MembershipContract.MembershipEntry.TABLE_NAME, null, cv);
                Log.d("database","Current user id: "+String.valueOf(id));

                finish();
            }
        });


        //database.close();

    }

    private String getDateCreated()
    {
        SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        return simple.format(date).toString().substring(0, 10);
    }

    private String getExpiryDate1()
    {
        String currDate=getDateCreated();
        return currDate.substring(0, 9)+"9";
    }

    private String getExpiryDate2()
    {
        String currDate=getDateCreated();
        return currDate.substring(0, 8)+"20";
    }

    private String getExpiryDate3()
    {
        String currDate=getDateCreated();
        return currDate.substring(0, 8)+"21";
    }
}
