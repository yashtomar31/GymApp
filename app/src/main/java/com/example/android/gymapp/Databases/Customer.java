package com.example.android.gymapp.Databases;

/**
 * Created by vishaal on 17/4/18.
 */

public class Customer
{
    public int customerID;
    public String name;
    public String age;
    public String dateJoined;
    public String address;
    public String phone;
    public String email;
    public String password;
    public int trainerID;

    public Customer(int cutomerID, String name, String address, String age, String dateJoined, String phone, String email, String password, int trainerID)
    {
        this.customerID=cutomerID;
        this.name=name;
        this.address=address;
        this.age=age;
        this.dateJoined=dateJoined;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.trainerID=trainerID;
    }

    @Override
    public String toString()
    {
        return String.valueOf(customerID)+":"+name+":"+age+":"+dateJoined+":"+address+":"+email+":"+phone+":"+String.valueOf(trainerID);
    }
}