package com.example.android.gymapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gymapp.Databases.CustomerContract;
import com.example.android.gymapp.Databases.DatabaseHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
{
    private static String email="";
    private static long cUserId;

    @InjectView(R.id.email_input)
    EditText emailEditText;
    @InjectView(R.id.password_input) EditText passwordEditText;

    private Button loginButton;
    private TextView linkToSignUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        ButterKnife.inject(this);

        loginButton= (Button) findViewById(R.id.btn_login);
        linkToSignUpTextView= (TextView) findViewById(R.id.link_signup);

        String emailEntered = emailEditText.getText().toString();
        String passwordEntered = passwordEditText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });

        linkToSignUpTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login()
    {

        Log.d("database","login");

        if(!validate())
        {
            return;
        }

        loginButton.setEnabled(false);
        final ProgressDialog progressDialog= new ProgressDialog(MainActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();
        Window window=progressDialog.getWindow();

        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        String email=emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();

        if(checkUser(email,password))
        {
            new android.os.Handler().postDelayed(

                    new Runnable() {
                        @Override
                        public void run() {
                            onLoginSuccess();
                            progressDialog.dismiss();
                        }
                    },
                    3000
            );
        }
        else
        {
            new android.os.Handler().postDelayed(

                    new Runnable() {
                        @Override
                        public void run() {
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    },
                    3000
            );
        }

    }

    private boolean validate()
    {
        boolean valid=true;
        String email=emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();

        if(email.isEmpty())
        {
            emailEditText.setError("Field cannot be empty");
            valid=false;
        }
        else {
            emailEditText.setError(null);
        }

        if(password.isEmpty())
        {
            passwordEditText.setError("Field cannot be empty");
            valid=false;
        }
        else
        {
            passwordEditText.setError(null);
        }
        return valid;
    }

    private void onLoginSuccess()
    {
        loginButton.setEnabled(true);
        Intent intent=new Intent(getApplicationContext(), DataActivity.class);

        startActivity(intent);
        finish();
    }

    private void onLoginFailed()
    {
        Toast.makeText(getApplicationContext(),"Sorry, we could not find your account.", Toast.LENGTH_SHORT).show();
        passwordEditText.setText("");
        loginButton.setEnabled(true);
    }

    private boolean checkUser(String email, String password)
    {
        String[] columns=new String[]{CustomerContract.CustomerEntry.COLUMN_CUSTOMERID};

        SQLiteDatabase database=(new DatabaseHelper(getApplicationContext())).getReadableDatabase();

        String selection= CustomerContract.CustomerEntry.COLUMN_EMAIL+"=? AND "+ CustomerContract.CustomerEntry.COLUMN_PASSWORD+"=?";

        String[] selectArgs={email,password};

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
            Log.d("database", "cursorCount="+String.valueOf(cursorCount));
            cursor.moveToFirst();
            cUserId=cursor.getLong(cursor.getColumnIndex(CustomerContract.CustomerEntry.COLUMN_CUSTOMERID));
            cursor.close();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        email=emailEditText.getText().toString();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        emailEditText.setText(email);
    }
}



