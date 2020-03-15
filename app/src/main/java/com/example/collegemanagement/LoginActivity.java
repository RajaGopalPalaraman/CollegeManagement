package com.example.collegemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        if (username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        } else {
            DBUtil.User user = DBUtil.validateUser(this, username.getText().toString(), password.getText().toString());
            if (user == null) {
                Toast.makeText(this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, user.getName() + " - Logged In", Toast.LENGTH_SHORT).show();
                HelperUtil.putUser(this, user);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }

}
