package com.example.android.gymapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
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
import com.example.android.gymapp.Databases.Trainer;
import com.example.android.gymapp.Databases.TrainerContract;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InsertTrainerActivity extends AppCompatActivity
{

    @InjectView(R.id.input_name_trainer)
    EditText nameEditText;
    @InjectView(R.id.input_phone_trainer)
    EditText phoneEditText;
    @InjectView(R.id.input_email_trainer)
    EditText emailEditText;
    @InjectView(R.id.input_level_trainer)
    EditText levelEditText;
    @InjectView(R.id.input_age_trainer)
    EditText ageEditText;

    private static Button addTrainer;


    private static String name="";
    private static String email="";
    private static String phone="";
    private static String level="";
    private static String age="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_trainer);


        ButterKnife.inject(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();



        addTrainer=(Button) findViewById(R.id.btn_add_trainer);

        addTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTrainer();
            }
        });

    }

    private void addNewTrainer()
    {
        Log.d("database", "add new trainer");

        if(!validate())
        {
            onFailure();
            return;
        }

        addTrainer.setEnabled(false);

        final ProgressDialog progressDialog= new ProgressDialog(InsertTrainerActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding New Trainer...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();
        Window window=progressDialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        String name=nameEditText.getText().toString();
        String email=emailEditText.getText().toString();
        String age=ageEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String level=levelEditText.getText().toString();


        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(TrainerContract.TrainerEntry.COLUMN_NAME, name);
        cv.put(TrainerContract.TrainerEntry.COLUMN_AGE, age);
        cv.put(TrainerContract.TrainerEntry.COLUMN_EMAIL, email);
        cv.put(TrainerContract.TrainerEntry.COLUMN_LEVEL, level);
        cv.put(TrainerContract.TrainerEntry.COLUMN_PHONE, phone);

        long id=database.insert(TrainerContract.TrainerEntry.TABLE_NAME, null, cv);
        Log.d("database", "Current user id: "+String.valueOf(id));

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

    private void onSuccess()
    {
        addTrainer.setEnabled(true);
        Intent intent=new Intent(getApplicationContext(),DataActivity.class);
        startActivity(intent);
        finish();
    }

    private void onFailure()
    {
        Toast.makeText(getApplicationContext(), "Unable to add new trainer", Toast.LENGTH_SHORT).show();
        addTrainer.setEnabled(true);
    }

    private boolean validate()
    {

        boolean valid = true;
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String level=levelEditText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameEditText.setError("Name should contain atleast 3 characters");
            valid = false;
        } else {
            nameEditText.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter valid email address");
            valid = false;
        }
        else {
            emailEditText.setError(null);
        }



        if(age.isEmpty()){
            ageEditText.setError("Enter valid age");
            valid=false;
        }
        else
        {
            ageEditText.setError(null);
        }

        if(phone.isEmpty())
        {
            phoneEditText.setError("Enter valid phone");valid=false;
        }
        else
        {
            phoneEditText.setError(null);
        }

        if(level.isEmpty())
        {
            levelEditText.setError("Enter valid level");valid=false;
        }
        else
        {
            levelEditText.setError(null);
        }

        return valid;

    }

    @Override
    protected void onPause() {
        super.onPause();
        name=nameEditText.getText().toString();
        email=emailEditText.getText().toString();
        age=ageEditText.getText().toString();
        phone=phoneEditText.getText().toString();
        level=levelEditText.getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        nameEditText.setText(name);
        emailEditText.setText(email);
        ageEditText.setText(age);
        phoneEditText.setText(phone);
        levelEditText.setText(level);
    }
}
