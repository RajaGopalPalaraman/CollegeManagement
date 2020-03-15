package com.example.collegemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationInfoActivity extends AppCompatActivity {

    static final String NOTIFICATION_ID = "notification_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_info);
        int id = getIntent().getIntExtra(NOTIFICATION_ID, -1);
        if (id == -1) {
            Toast.makeText(this, "Notification not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        DBUtil.Notification notification = DBUtil.getNotification(this, id);
        TextView textView = findViewById(R.id.notification_title);
        textView.setText(notification.getTitle());
        textView = findViewById(R.id.notification_description);
        textView.setText(notification.getDescription());
        textView = findViewById(R.id.notification_added_by);
        textView.setText(notification.getAddedBy());
    }
}
