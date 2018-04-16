package com.example.android.gymapp.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vishaal on 16/4/18.
 */

public class EquipmentCheckoutRelationContract
{
    public static final String AUTHORITY="com.example.android.gymapp";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+AUTHORITY);

    public static final String PATH_EQUIPMENT_CHECKOUT_RELATION="equipment_checkout_relation";

    public static final class EquipmentCheckoutRelationEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "equipment_checkout_relation";
        public static final String COLUMN_EQUIPMENTCHECKOUTID = "equipment_checkout_id";
        public static final String COLUMN_CHECKOUTDATE = "checkout_date";
        public static final String COLUMN_DUEDAY = "due_day";
        public static final String COLUMN_EQUIPMENTID = "equipment_id";
        public static final String COLUMN_CUSTOMERID = "customer_id";
    }
}
