package com.example.collegemanagement;

import com.example.collegemanagement.tables.NotificationTable;
import com.example.collegemanagement.tables.UserCredentials;

import java.util.HashMap;

public class AppConstants {

    static final String LOG_TAG = "COLLEGE_LOG_TAG";
    static final String DATABASE_NAME = "CollegeManagement";
    static final int DATABASE_VERSION = 1;
    static final HashMap<String,HashMap<String,String>> TABLE_LIST;

    static
    {
        TABLE_LIST = new HashMap<>();
        TABLE_LIST.put(UserCredentials.TABLE_NAME, UserCredentials.getTableProperties());
        TABLE_LIST.put(NotificationTable.TABLE_NAME, NotificationTable.getTableProperties());
    }

}
