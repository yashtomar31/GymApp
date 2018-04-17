package com.example.android.gymapp.Databases;

/**
 * Created by vishaal on 18/4/18.
 */

public class Equipment
{
    private int equipmentID;
    private String name;
    private String type;

    public Equipment(int equipmentID, String name, String type)
    {
        this.equipmentID=equipmentID;
        this.name=name;
        this.type=type;
    }
}
