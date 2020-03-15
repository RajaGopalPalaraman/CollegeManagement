package com.example.collegemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.collegemanagement.tables.UserCredentials;

import java.util.HashMap;
import java.util.Set;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Set<String> tables = AppConstants.TABLE_LIST.keySet();
        for(String table : tables)
        {
            String createQuery = "CREATE TABLE "+table+"(";
            HashMap<String,String> tableProperties = AppConstants.TABLE_LIST.get(table);
            Set<String> columns = tableProperties.keySet();
            boolean appendSeparator = false;
            for(String column : columns)
            {
                if(appendSeparator)
                {
                    createQuery = createQuery + ",";
                }
                createQuery = createQuery + column;
                createQuery = createQuery + " ";
                createQuery = createQuery + tableProperties.get(column);
                appendSeparator = true;
            }
            createQuery = createQuery+")";
            db.execSQL(createQuery);
            Log.d(AppConstants.LOG_TAG,"Created Table : "+table);
        }
        createUsers(db);
    }

    private void createUsers(SQLiteDatabase database) {
        // 2 staff account
        for (int i=1; i<3; i++) {
            ContentValues values = new ContentValues();
            values.put(UserCredentials.NAME, "Staff" + i);
            values.put(UserCredentials.USER_ID, "staff" + i);
            values.put(UserCredentials.PASSWORD, "password_staff" + i);
            values.put(UserCredentials.USER_TYPE, DBUtil.User.STAFF);
            database.insert(UserCredentials.TABLE_NAME, null, values);
        }

        // 5 student account
        for (int i=1; i<6; i++) {
            ContentValues values = new ContentValues();
            values.put(UserCredentials.NAME, "Student" + i);
            values.put(UserCredentials.USER_ID, "student" + i);
            values.put(UserCredentials.PASSWORD, "password_student" + i);
            values.put(UserCredentials.USER_TYPE, DBUtil.User.STUDENT);
            database.insert(UserCredentials.TABLE_NAME, null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Set<String> tables = AppConstants.TABLE_LIST.keySet();
        for(String table : tables)
        {
            db.execSQL("DROP TABLE IF EXISTS "+table);
        }
        onCreate(db);
    }

}
