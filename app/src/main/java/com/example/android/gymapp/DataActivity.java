package com.example.android.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.gymapp.DisplayCustomerActivity;

import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity
{

    Button logoutButton;
    Button showCustomers;
    Button insertEquipment;
    Button insertTrainer;
    Button showTrainers;
    Button showEquipments;
    Button getMembership;
    Button selectTrainer;

    public static long currentID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        currentID=(Long) getIntent().getExtras().get("ID");
        Log.d("database", String.valueOf(currentID));

        logoutButton=(Button) findViewById(R.id.btn_logout);
        showCustomers=(Button) findViewById(R.id.btn_show_customers);
        insertEquipment=(Button) findViewById(R.id.btn_insert_equipment);
        insertTrainer=(Button) findViewById(R.id.btn_insert_trainer);
        showEquipments=(Button) findViewById(R.id.btn_show_equipments);
        showTrainers=(Button) findViewById(R.id.btn_show_trainers);
        getMembership=(Button) findViewById(R.id.btn_get_membership);
        selectTrainer=(Button) findViewById(R.id.btn_select_trainer);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        getMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), GetMembershipActivity.class);
                startActivity(intent);
            }
        });

        selectTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SelectTrainerActivity.class);
                startActivity(intent);
            }
        });


        showCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DisplayCustomerActivity.class);
                startActivity(intent);
            }
        });

        showEquipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DisplayEquipmentActivity.class);
                startActivity(intent);
            }
        });

        showTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DisplayTrainerActivity.class);
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
