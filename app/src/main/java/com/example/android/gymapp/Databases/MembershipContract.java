package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class MembershipContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_MEMBERSHIP="membership";

    public static final class MembershipEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "membership";
        public static final String COLUMN_MEMBERSHIPID = "membership_id";
        public static final String COLUMN_MEMBERSHIPRATE = "membership_rate";
        public static final String COLUMN_EXPIRYDATE = "expiry_date";
        public static final String COLUMN_MEMBERSHIPLENGTH = "membership_length";
        public static final String COLUMN_DATECREATED = "date_created";
        public static final String COLUMN_CUSTOMERID = "customer_id";
    }
}
