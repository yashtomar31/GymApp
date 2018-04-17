package com.example.android.gymapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gymapp.Databases.CustomerContract;
import com.example.android.gymapp.Databases.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity
{

    @InjectView(R.id.input_name)
    EditText nameEditText;
    @InjectView(R.id.input_email)
    EditText emailEditText;
    @InjectView(R.id.input_password)
    EditText passwordEditText;
    @InjectView(R.id.input_confirm_password)
    EditText passwordConfirmEditText;
    @InjectView(R.id.input_address)
    EditText addressEditText;
    @InjectView(R.id.input_age)
    EditText ageEditText;
    @InjectView(R.id.input_phone)
    EditText phoneEditText;

    private static Button signUpButton;
    private static TextView linkToLogin;

    private static String name="";
    private static String email="";
    private static String age="";
    private static String phone="";
    private static String address="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ButterKnife.inject(this);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        signUpButton=(Button) findViewById(R.id.btn_signup);
        linkToLogin = (TextView) findViewById(R.id.link_login);


        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signup();
            }
        });

        linkToLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });


    }

    private void signup()
    {
        Log.d("database", "signup");

        if(!validate())
        {
            onSignUpFailed();
            return;
        }

        signUpButton.setEnabled(false);
        final ProgressDialog progressDialog= new ProgressDialog(SignupActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();
        Window window=progressDialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        String name=nameEditText.getText().toString();
        String email=emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String age=ageEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String address=addressEditText.getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CustomerContract.CustomerEntry.COLUMN_NAME, name);
        cv.put(CustomerContract.CustomerEntry.COLUMN_EMAIL, email);
        cv.put(CustomerContract.CustomerEntry.COLUMN_PASSWORD, password);
        cv.put(CustomerContract.CustomerEntry.COLUMN_ADDRESS, address);
        cv.put(CustomerContract.CustomerEntry.COLUMN_AGE, age);
        cv.put(CustomerContract.CustomerEntry.COLUMN_DATE_JOINED, getDateJoined());
        cv.put(CustomerContract.CustomerEntry.COLUMN_PHONE, phone);
        cv.put(CustomerContract.CustomerEntry.COLUMN_TRAINERID, "");


        long id=database.insert(CustomerContract.CustomerEntry.TABLE_NAME, null, cv);
        Log.d("database","Current user id: "+String.valueOf(id));

        database.close();

        new android.os.Handler().postDelayed(

                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        onSignUpSuccess();
                        progressDialog.dismiss();
                    }
                },
                3000
        );

    }

    private String getDateJoined()
    {
        SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        return simple.format(date).toString().substring(0, 10);
    }

    private void onSignUpFailed()
    {
        Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_SHORT).show();
        signUpButton.setEnabled(true);
    }

    private void onSignUpSuccess()
    {
        signUpButton.setEnabled(true);
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("ACTIONBAR", nameEditText.getText().toString());
        startActivity(intent);
        finish();
    }

    private boolean validate()
    {
        boolean valid = true;
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String address=addressEditText.getText().toString();
        String confirmPassword= passwordConfirmEditText.getText().toString();

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
        else if(checkEmail(email)){
            emailEditText.setError("Email address exists. Please login");
            valid=false;
        }
        else {
            emailEditText.setError(null);
        }

        //PasswordValidator passwordValidator = new PasswordValidator();
        if (password.isEmpty()) {
            passwordEditText.setError("Enter valid password");
            valid = false;
        } else {
            passwordEditText.setError(null);
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

        if(address.isEmpty())
        {
            addressEditText.setError("Enter valid address");valid=false;
        }
        else
        {
            addressEditText.setError(null);
        }

        if(!confirmPassword.equals(password))
        {
            passwordConfirmEditText.setError("Passwords do not match");
            valid=false;
        }
        else
        {
            passwordConfirmEditText.setError(null);
        }

        return valid;
    }

    private boolean checkEmail(String email)
    {
        String[] columns=new String[]{CustomerContract.CustomerEntry.COLUMN_CUSTOMERID};

        SQLiteDatabase database=(new DatabaseHelper(getApplicationContext())).getReadableDatabase();

        String selection= CustomerContract.CustomerEntry.COLUMN_EMAIL+"=?";

        String[] selectArgs={email};

        Cursor cursor= database.query(
                CustomerContract.CustomerEntry.TABLE_NAME,
                columns,
                selection,
                selectArgs,
                null,
                null,
                null);

        int cursorCount=cursor.getCount();

        //cursor.close();
        database.close();

        if(cursorCount>0)
        {
            // email id exists
            Log.d("database", "cursorCount="+String.valueOf(cursorCount));
            //cursor.moveToFirst();
            //CurrentUserId=cursor.getLong(cursor.getColumnIndex(ProfileContract.ProfileEntry._ID));
            //cursor.close();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        name=nameEditText.getText().toString();
        email=emailEditText.getText().toString();
        age=ageEditText.getText().toString();
        phone=phoneEditText.getText().toString();
        address=addressEditText.getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();

        nameEditText.setText(name);
        emailEditText.setText(email);
        ageEditText.setText(age);
        phoneEditText.setText(phone);
        addressEditText.setText(address);
    }
}
