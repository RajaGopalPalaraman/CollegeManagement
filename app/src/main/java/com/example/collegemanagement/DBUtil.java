package com.example.collegemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.collegemanagement.tables.NotificationTable;
import com.example.collegemanagement.tables.UserCredentials;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static final String SELECT_USER_QUERY = "SELECT * FROM "+ UserCredentials.TABLE_NAME +" WHERE "+UserCredentials.USER_ID+"=? AND "+UserCredentials.PASSWORD+"=?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM "+ UserCredentials.TABLE_NAME +" WHERE "+UserCredentials.S_NO+"=?";
    private static final String SELECT_NOTIFICATION_BY_ID_QUERY = "SELECT * FROM "+ NotificationTable.TABLE_NAME +" WHERE "+NotificationTable.S_NO+"=?";
    private static final String SELECT_NOTIFICATION_QUERY = "SELECT * FROM "+ NotificationTable.TABLE_NAME +" ORDER BY "+NotificationTable.S_NO + " DESC";

    static User validateUser(Context context, String username, String password) {
        User user = null;
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase database = databaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_USER_QUERY, new String[]{username, password});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserCredentials.S_NO)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserCredentials.NAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(UserCredentials.USER_ID)));
                user.setUserType(cursor.getInt(cursor.getColumnIndex(UserCredentials.USER_TYPE)));
            }
            cursor.close();
            database.close();
            databaseHandler.close();
        }
        return user;
    }

    static User getUser(Context context, int id) {
        User user = null;
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase database = databaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_USER_BY_ID_QUERY, new String[]{String.valueOf(id)});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserCredentials.S_NO)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserCredentials.NAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(UserCredentials.USER_ID)));
                user.setUserType(cursor.getInt(cursor.getColumnIndex(UserCredentials.USER_TYPE)));
            }
            cursor.close();
            database.close();
            databaseHandler.close();
        }
        return user;
    }

    static boolean createNotification(Context context, String title, String description, String addedBy) {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotificationTable.TITLE, title);
        values.put(NotificationTable.DESCRIPTION, description);
        values.put(NotificationTable.ADDED_BY, addedBy);

        long status = database.insert(NotificationTable.TABLE_NAME, null, values);
        database.close();
        return status != -1;
    }

    static Notification getNotification(Context context, int id) {
        Notification notification = null;
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase database = databaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_NOTIFICATION_BY_ID_QUERY, new String[]{String.valueOf(id)});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                notification = new Notification();
                notification.setId(cursor.getInt(cursor.getColumnIndex(NotificationTable.S_NO)));
                notification.setTitle(cursor.getString(cursor.getColumnIndex(NotificationTable.TITLE)));
                notification.setDescription(cursor.getString(cursor.getColumnIndex(NotificationTable.DESCRIPTION)));
                notification.setAddedBy(cursor.getString(cursor.getColumnIndex(NotificationTable.ADDED_BY)));
            }
            cursor.close();
            database.close();
            databaseHandler.close();
        }
        return notification;
    }

    static List<Notification> getNotificationList(Context context) {
        List<Notification> notifications = new ArrayList<>();
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase database = databaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_NOTIFICATION_QUERY, new String[0]);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Notification notification = new Notification();
                notification.setId(cursor.getInt(cursor.getColumnIndex(NotificationTable.S_NO)));
                notification.setTitle(cursor.getString(cursor.getColumnIndex(NotificationTable.TITLE)));
                notification.setDescription(cursor.getString(cursor.getColumnIndex(NotificationTable.DESCRIPTION)));
                notification.setAddedBy(cursor.getString(cursor.getColumnIndex(NotificationTable.ADDED_BY)));
                notifications.add(notification);
            }
            cursor.close();
            database.close();
            databaseHandler.close();
        }
        return notifications;
    }

    public static class User {

        static final int STUDENT = 0;
        static final int STAFF = 1;

        private int id;
        private String name;
        private String username;
        private int userType;

        public int getId() {
            return id;
        }

        private void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        private void setUsername(String username) {
            this.username = username;
        }

        public int getUserType() {
            return userType;
        }

        private void setUserType(int userType) {
            this.userType = userType;
        }
    }

    public static class Notification {
        private int id;
        private String title;
        private String description;
        private String addedBy;

        public int getId() {
            return id;
        }

        private void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        private void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        private void setDescription(String description) {
            this.description = description;
        }

        public String getAddedBy() {
            return addedBy;
        }

        private void setAddedBy(String addedBy) {
            this.addedBy = addedBy;
        }
    }

}
