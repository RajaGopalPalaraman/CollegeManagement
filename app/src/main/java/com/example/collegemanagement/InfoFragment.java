package com.example.collegemanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DBUtil.User user = HelperUtil.getUser(getContext());
        TextView textView = getActivity().findViewById(R.id.profile_text);
        String builder = "       Name: " +
                user.getName() +
                "\r\n" +
                "Username: " +
                user.getUsername();
        textView.setText(builder);
    }

}
