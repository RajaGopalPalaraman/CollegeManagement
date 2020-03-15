package com.example.collegemanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddNotificationFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = getActivity().findViewById(R.id.add);
        button.setOnClickListener((v) -> {
            TextView titleView = getActivity().findViewById(R.id.notification_title);
            TextView descriptionView = getActivity().findViewById(R.id.notification_description);
            if (titleView.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Title not provided", Toast.LENGTH_SHORT).show();
            } else if (descriptionView.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Description required", Toast.LENGTH_SHORT).show();
            } else {
                if (DBUtil.createNotification(getContext(), titleView.getText().toString(),
                        descriptionView.getText().toString(), HelperUtil.getUser(getContext()).getUsername())) {
                    titleView.setText("");
                    descriptionView.setText("");
                    Toast.makeText(getContext(), "Notification added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Title already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
