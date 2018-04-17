package com.example.android.gymapp.Databases;

/**
 * Created by vishaal on 18/4/18.
 */

public class Trainer
{

    private int trainerID;
    private String name;
    private String level;
    private String age;
    private String phone;
    private String email;

    public Trainer(int trainerID, String name, String level, String age, String phone, String email)
    {
        this.trainerID=trainerID;
        this.name=name;
        this.level=level;
        this.email=email;
        this.age=age;
        this.phone=phone;
    }

    @Override
    public String toString()
    {
        return String.valueOf(trainerID)+":"+name+":"+level+":"+age+":"+email+":"+phone;
    }
}
