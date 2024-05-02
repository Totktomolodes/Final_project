package com.example.final_book_explorer_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;
import com.example.final_book_explorer_project.fragments.user_profile.Profile_Fragment;

import java.time.chrono.IsoChronology;

public class MainActivity2 extends AppCompatActivity {
    private Profile_Fragment profileFragment = new Profile_Fragment();
    private Button catalog, mycatalog, installer, user_profile;
    private FrameLayout Frame_transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        catalog = findViewById(R.id.catalog);
        mycatalog = findViewById(R.id.mycatalog);
        installer = findViewById(R.id.installer);
        user_profile = findViewById(R.id.user_profile);
        Frame_transition = findViewById(R.id.Frame_transition);


        setNewFragment(profileFragment); // по умолчанию страница


        installer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstallerFragment installerFragment = new InstallerFragment();
                setNewFragment(installerFragment);
            }

        });

        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile_Fragment profileFragment1 = new Profile_Fragment();
                setNewFragment(profileFragment1);
            }
        });
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Frame_transition, fragment);
        ft.commit();

    }
}