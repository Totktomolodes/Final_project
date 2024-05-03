package com.example.final_book_explorer_project.fragments.user_profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View view = getView();
        if (view == null){
            throw new RuntimeException();
        }
        log_out_button = view.findViewById(R.id.log_out_button);
        settings_button = view.findViewById(R.id.settings_button);
        changing_button = view.findViewById(R.id.changing_button);


        changing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });




//Todo        public void logout(){
//Todo        public void settings(){

    }



}


