package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class ManagerContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_MANAGER="manager";

    public static final class ManagerEntry implements BaseColumns
    {
        public static final String TABLE_NAME="manager";
        public static final String COLUMN_MANAGERID="manager_id";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_LEVEL="level";
        public static final String COLUMN_TITLE="title";
    }
}
