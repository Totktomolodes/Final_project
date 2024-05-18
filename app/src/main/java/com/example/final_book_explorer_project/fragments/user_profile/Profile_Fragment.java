package com.example.final_book_explorer_project.fragments.user_profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity2;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_Fragment extends Fragment {

    private Button log_out_button, settings_button, changing_button;
    private TextView profile_main_textView, username_textView, password_textView, textView_of_username, textView_of_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_, container, false);

    }
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log_out_button = view.findViewById(R.id.log_out_button);
        settings_button = view.findViewById(R.id.settings_button);
        changing_button = view.findViewById(R.id.changing_button);


        changing_button.setOnClickListener(view1 -> {

        });

        settings_button.setOnClickListener(view1 -> {
            MainActivity2 mainActivity2 = new MainActivity2();
           Settings_fragment settings_fragment = new Settings_fragment();
           mainActivity2.setNewFragment(settings_fragment);
        });
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

    }



}


