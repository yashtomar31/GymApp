package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class CustomerContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_CUSTOMER="customer";

    public static final class CustomerEntry implements BaseColumns
    {
        public static final String TABLE_NAME="customer";
        public static final String COLUMN_CUSTOMERID="customer_id";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_AGE="age";
        public static final String COLUMN_DATE_JOINED="date_joined";
        public static final String COLUMN_ADDRESS="address";
        public static final String COLUMN_PHONE="phone";
        public static final String COLUMN_EMAIL="email";
        public static final String COLUMN_PASSWORD="password";
        public static final String COLUMN_TRAINERID="trainer_id";
    }
}
