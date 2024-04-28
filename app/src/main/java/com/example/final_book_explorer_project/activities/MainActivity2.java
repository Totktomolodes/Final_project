package com.example.final_book_explorer_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;

import java.time.chrono.IsoChronology;

public class MainActivity2 extends AppCompatActivity {
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

        setInstallerFragment();
        catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInstallerFragment();
            }
        });

    }

    private void setInstallerFragment() {
        InstallerFragment installerFragment = new InstallerFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Frame_transition, installerFragment);
    }
}