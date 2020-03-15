package com.example.collegemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_index, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getActivity().findViewById(R.id.notification_list);
        List<DBUtil.Notification> notifications = DBUtil.getNotificationList(getContext());
        List<String> strings = new ArrayList<>();
        for (DBUtil.Notification notification : notifications) {
            strings.add(notification.getTitle());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.list_layout, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(getContext(), NotificationInfoActivity.class).
                    putExtra(NotificationInfoActivity.NOTIFICATION_ID, notifications.get(position).getId()));
        });
    }

}
