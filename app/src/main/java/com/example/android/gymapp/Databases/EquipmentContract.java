package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class EquipmentContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_EQUIPMENT="equipment";

    public static final class EquipmentEntry implements BaseColumns
    {
        public static final String TABLE_NAME="equipment";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_TYPE="type";
    }
}
