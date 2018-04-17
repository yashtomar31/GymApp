package com.example.android.gymapp.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishaal on 16/4/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME="gymDb.db";
    private static final int DATABASE_VERSION=1;

    private static final String CREATE_TABLE_EQUIPMENT="CREATE TABLE "+ EquipmentContract.EquipmentEntry.TABLE_NAME+" ( "
            + EquipmentContract.EquipmentEntry.COLUMN_EQUIPMENTID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EquipmentContract.EquipmentEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + EquipmentContract.EquipmentEntry.COLUMN_TYPE+" TEXT NOT NULL "+");";

    private static final String CREATE_TABLE_CUSTOMER="CREATE TABLE "+ CustomerContract.CustomerEntry.TABLE_NAME+" ( "
            + CustomerContract.CustomerEntry.COLUMN_CUSTOMERID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CustomerContract.CustomerEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_AGE+" INTEGER NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_DATE_JOINED+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_ADDRESS+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_EMAIL+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_PHONE+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_PASSWORD+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_TRAINERID+" INTEGER, "
            + "FOREIGN KEY ("+ CustomerContract.CustomerEntry.COLUMN_TRAINERID+") REFERENCES "+ TrainerContract.TrainerEntry.TABLE_NAME+"("+ TrainerContract.TrainerEntry.COLUMN_TRAINERID+")"+");";

    private static final String CREATE_TABLE_TRAINER="CREATE TABLE "+ TrainerContract.TrainerEntry.TABLE_NAME+" ( "
            + TrainerContract.TrainerEntry.COLUMN_TRAINERID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrainerContract.TrainerEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + TrainerContract.TrainerEntry.COLUMN_LEVEL+" TEXT NOT NULL, "
            + TrainerContract.TrainerEntry.COLUMN_AGE+" INTEGER NOT NULL, "
            + TrainerContract.TrainerEntry.COLUMN_EMAIL+" TEXT NOT NULL, "
            + TrainerContract.TrainerEntry.COLUMN_PHONE+" TEXT NOT NULL "+");";

    private static final String CREATE_TABLE_EQUIPMENT_CHECKOUT_RELATION="CREATE TABLE "+ EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.TABLE_NAME+" ( "
            + EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_EQUIPMENTCHECKOUTID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_CHECKOUTDATE+" TEXT NOT NULL, "
            + EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_DUEDAY+" TEXT NOT NULL, "
            + EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_CUSTOMERID+" INTEGER, "
            + EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_EQUIPMENTID+" INTEGER, "
            + "FOREIGN KEY ("+ EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_CUSTOMERID+") REFERENCES "+ CustomerContract.CustomerEntry.TABLE_NAME+"("+ CustomerContract.CustomerEntry.COLUMN_CUSTOMERID+"), "
            + "FOREIGN KEY ("+ EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.COLUMN_EQUIPMENTID+") REFERENCES "+ EquipmentContract.EquipmentEntry.TABLE_NAME+"("+ EquipmentContract.EquipmentEntry.COLUMN_EQUIPMENTID+")"+");";


    private static final String CREATE_TABLE_MEMBERSHIP="CREATE TABLE "+ MembershipContract.MembershipEntry.TABLE_NAME+" ( "
            + MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MembershipContract.MembershipEntry.COLUMN_DATECREATED+" TEXT NOT NULL, "
            + MembershipContract.MembershipEntry.COLUMN_EXPIRYDATE+" TEXT NOT NULL, "
            + MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPLENGTH+" TEXT NOT NULL, "
            + MembershipContract.MembershipEntry.COLUMN_MEMBERSHIPRATE+" TEXT NOT NULL, "
            + MembershipContract.MembershipEntry.COLUMN_CUSTOMERID+" INTEGER, "
            + "FOREIGN KEY ("+ MembershipContract.MembershipEntry.COLUMN_CUSTOMERID+") REFERENCES "+ CustomerContract.CustomerEntry.TABLE_NAME+"("+ CustomerContract.CustomerEntry.COLUMN_CUSTOMERID+")"+");";

    private static final String CREATE_TABLE_EQUIPMENT_TRAINER_RELATION="CREATE TABLE "+ EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.TABLE_NAME+" ( "
            + EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.COLUMN_EQUIPMENTTRAINERID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.COLUMN_EQUIPMENTID+" INTEGER, "
            + EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.COLUMN_TRAINERID+" INTEGER, "
            + "FOREIGN KEY ("+ EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.COLUMN_TRAINERID+") REFERENCES "+ TrainerContract.TrainerEntry.TABLE_NAME+"("+ TrainerContract.TrainerEntry.COLUMN_TRAINERID+"), "
            + "FOREIGN KEY ("+ EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.COLUMN_EQUIPMENTID+") REFERENCES "+ EquipmentContract.EquipmentEntry.TABLE_NAME+"("+ EquipmentContract.EquipmentEntry.COLUMN_EQUIPMENTID+")"+");";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUIPMENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_CUSTOMER);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRAINER);
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUIPMENT_CHECKOUT_RELATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_MEMBERSHIP);
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUIPMENT_TRAINER_RELATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EquipmentContract.EquipmentEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ CustomerContract.CustomerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TrainerContract.TrainerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EquipmentCheckoutRelationContract.EquipmentCheckoutRelationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MembershipContract.MembershipEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EquipmentTrainerRelationContract.EquipmentTrainerRelationEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}