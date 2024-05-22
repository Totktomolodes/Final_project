package com.example.final_book_explorer_project.fragments.user_profile;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity2;

public class Profile_Fragment extends Fragment {

    private Button closing_btn, settings_button, changing_button;
    private TextView profile_main_textView, username_textView, password_textView, textView_of_username, textView_of_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_, container, false);

    }
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closing_btn = view.findViewById(R.id.log_out_button);
        settings_button = view.findViewById(R.id.settings_button);
        changing_button = view.findViewById(R.id.changing_button);


        changing_button.setOnClickListener(view1 -> {

        });

        settings_button.setOnClickListener(view1 -> {
            MainActivity2 mainActivity2 = new MainActivity2();
           Settings_fragment settings_fragment = new Settings_fragment();
           mainActivity2.setNewFragment(settings_fragment);
        });
        closing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setExitAlertDialog();

            }

        });

    }
    public void setExitAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Внимание!");
        builder.setMessage("Вы действительно хотите закрыть приложение?");
        builder.setCancelable(false);
        builder.setPositiveButton("Конечно", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}


