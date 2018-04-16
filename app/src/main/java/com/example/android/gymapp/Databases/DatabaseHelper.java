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

    private static final String CREATE_TABLE_EQUIPMENT="CREATE TABLE "+EquipmentContract.EquipmentEntry.TABLE_NAME+" ( "
            + EquipmentContract.EquipmentEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EquipmentContract.EquipmentEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + EquipmentContract.EquipmentEntry.COLUMN_TYPE+" TEXT NOT NULL "+");";

    private static final String CREATE_TABLE_CUSTOMER="CREATE TABLE "+CustomerContract.CustomerEntry.TABLE_NAME+" ( "
            + CustomerContract.CustomerEntry.COLUMN_CUSTOMERID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CustomerContract.CustomerEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_AGE+" INTEGER NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_DATE_JOINED+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_ADDRESS+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_EMAIL+" TEXT NOT NULL, "
            + CustomerContract.CustomerEntry.COLUMN_PHONE+" TEXT NOT NULL "+");";

    private static final String CREATE_TABLE_MANAGER="CREATE TABLE "+ ManagerContract.ManagerEntry.TABLE_NAME+" ( "
            + ManagerContract.ManagerEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ManagerContract.ManagerEntry.COLUMN_NAME+" TEXT NOT NULL, "
            + ManagerContract.ManagerEntry.COLUMN_LEVEL+" TEXT NOT NULL, "
            + ManagerContract.ManagerEntry.COLUMN_TITLE+" TEXT NOT NULL "+");";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUIPMENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_CUSTOMER);
        sqLiteDatabase.execSQL(CREATE_TABLE_MANAGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EquipmentContract.EquipmentEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ CustomerContract.CustomerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ManagerContract.ManagerEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}