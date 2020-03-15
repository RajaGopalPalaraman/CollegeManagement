package com.example.collegemanagement;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

class HelperUtil {

    private static final String USER = "user";
    private static final String PREFERENCE_KEY = "college_preference";

    static DBUtil.User getUser(Context context) {
        DBUtil.User user = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFERENCE_KEY, Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(USER, -1);
        if (id != -1) {
            user = DBUtil.getUser(context, id);
        }
        return user;
    }

    static void putUser(Context context, @Nullable DBUtil.User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFERENCE_KEY, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(USER, user == null ? -1 : user.getId()).apply();
    }

}
