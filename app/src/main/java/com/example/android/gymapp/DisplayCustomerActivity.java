package com.example.android.gymapp.Databases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.gymapp.R;

import java.util.ArrayList;

public class DisplayCustomerActivity extends AppCompatActivity {


    TextView addCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_customer);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        addCustomer=(TextView) findViewById(R.id.add_customer);

        ArrayList<Customer> customers=new ArrayList<Customer>();

        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();

        String selectQuery="SELECT * FROM "+ CustomerContract.CustomerEntry.TABLE_NAME;

        Cursor cursor=database.rawQuery(selectQuery, null);

        String outputText="";
        outputText+="CustomerID:Name:Age:Date Joined:Address:Email:Phone:TrainerID\n";

        if(cursor.moveToFirst())
        {
            do
            {
                Customer addCustomer=new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(4), cursor.getString(2), cursor.getString(3), cursor.getString(6), cursor.getString(5), cursor.getString(7), cursor.getInt(8));
                Log.d("database", addCustomer.toString());
                outputText+=addCustomer.toString();
                outputText+="\n";
            }while(cursor.moveToNext());
        }

        Log.d("database", outputText);
        addCustomer.setText(outputText);

        database.close();

    }
}
