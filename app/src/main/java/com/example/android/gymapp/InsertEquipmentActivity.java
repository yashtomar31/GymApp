package com.example.android.gymapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.gymapp.Databases.DatabaseHelper;
import com.example.android.gymapp.Databases.EquipmentContract;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InsertEquipmentActivity extends AppCompatActivity
{

    @InjectView(R.id.input_name_equipment)
    EditText nameEditText;
    @InjectView(R.id.input_type_equipment)
    EditText typeEditText;


    private static Button addEquipment;

    private static String name="";
    private static String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_equipment);

        ButterKnife.inject(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();



        addEquipment=(Button) findViewById(R.id.btn_add_equipment);

        addEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEquipment();
            }
        });

    }

    private void addNewEquipment()
    {
        Log.d("database", "add new equipment");

        if(!validate())
        {
            onFailure();
            return;
        }

        addEquipment.setEnabled(false);

        final ProgressDialog progressDialog= new ProgressDialog(InsertEquipmentActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding New Equipment...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();
        Window window=progressDialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        String name=nameEditText.getText().toString();
        String type=typeEditText.getText().toString();

        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database=databaseHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put(EquipmentContract.EquipmentEntry.COLUMN_NAME, name);
        cv.put(EquipmentContract.EquipmentEntry.COLUMN_TYPE, type);

        long id=database.insert(EquipmentContract.EquipmentEntry.TABLE_NAME, null, cv);

        Log.d("database", "Currer user id: "+String.valueOf(id));
        database.close();

        new android.os.Handler().postDelayed(

                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        onSuccess();
                        progressDialog.dismiss();
                    }
                },
                3000
        );

    }

    private void onFailure()
    {
        Toast.makeText(getApplicationContext(), "Unable to add new equipment", Toast.LENGTH_SHORT).show();
        addEquipment.setEnabled(true);
    }


    private boolean validate()
    {
        boolean valid = true;
        String name = nameEditText.getText().toString();
        String type = typeEditText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameEditText.setError("Name should contain atleast 3 characters");
            valid = false;
        } else {
            nameEditText.setError(null);
        }

        if(type.isEmpty())
        {
            typeEditText.setError("Enter valid type");valid=false;
        }
        else
        {
            typeEditText.setError(null);
        }



        return valid;
    }

    private void onSuccess()
    {
        addEquipment.setEnabled(true);
        Intent intent=new Intent(getApplicationContext(),DataActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        name=nameEditText.getText().toString();
        type=typeEditText.getText().toString();

    }

    @Override
    protected void onStart() {
        super.onStart();
        nameEditText.setText(name);
        typeEditText.setText(type);
    }

}
