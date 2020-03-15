package com.example.collegemanagement.tables;

import java.util.HashMap;

public class NotificationTable {

    public static final String TABLE_NAME = "Notifications";

    public static final String S_NO = "Sno";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ADDED_BY = "added_by";

    public static HashMap<String,String> getTableProperties()
    {
        HashMap<String,String> tableProperties = new HashMap<>();
        tableProperties.put(S_NO,"INTEGER PRIMARY KEY");
        tableProperties.put(TITLE,"TEXT NOT NULL UNIQUE");
        tableProperties.put(DESCRIPTION,"TEXT NOT NULL");
        tableProperties.put(ADDED_BY,"TEXT NOT NULL");
        return tableProperties;
    }

}
