package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class TrainerContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_TRAINER="trainer";

    public static final class TrainerEntry implements BaseColumns
    {
        public static final String TABLE_NAME="trainer";
        public static final String COLUMN_TRAINERID="trainer_id";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_LEVEL="level";
        public static final String COLUMN_AGE="age";
        public static final String COLUMN_PHONE="phone";
        public static final String COLUMN_EMAIL="email";
    }
}
