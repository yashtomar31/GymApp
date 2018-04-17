package com.example.android.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.gymapp.Databases.DisplayCustomerActivity;

public class DataActivity extends AppCompatActivity
{

    Button logoutButton;
    Button showCustomers;
    Button insertEquipment;
    Button insertTrainer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        logoutButton=(Button) findViewById(R.id.btn_logout);
        showCustomers=(Button) findViewById(R.id.btn_show_customers);
        insertEquipment=(Button) findViewById(R.id.btn_insert_equipment);
        insertTrainer=(Button) findViewById(R.id.btn_insert_trainer);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        showCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DisplayCustomerActivity.class);
                startActivity(intent);
            }
        });

        insertEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), InsertEquipmentActivity.class);
                startActivity(intent);
            }
        });

        insertTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), InsertTrainerActivity.class);
                startActivity(intent);
            }
        });

    }
}
