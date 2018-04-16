package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class EquipmentTrainerRelationContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_EQUIPMENT_TRAINER_RELATION="equipment_trainer_relation";

    public static final class EquipmentTrainerRelationEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "equipment_trainer_relation";
        public static final String COLUMN_EQUIPMENTTRAINERID = "equipment_trainer_id";
        public static final String COLUMN_EQUIPMENTID = "equipment_id";
        public static final String COLUMN_TRAINERID = "trainer_id";
    }
}


