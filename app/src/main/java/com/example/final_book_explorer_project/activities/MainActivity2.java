package com.example.final_book_explorer_project.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.catalog.CatalogFragment;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;
import com.example.final_book_explorer_project.fragments.mycatalog.MyCatalogFragment;
import com.example.final_book_explorer_project.fragments.user_profile.Profile_Fragment;

public class MainActivity2 extends AppCompatActivity {
    private Profile_Fragment profileFragment = new Profile_Fragment();
    private ImageButton catalog, mycatalog, installer, user_profile, closing_button;
    private FrameLayout frameTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        catalog = findViewById(R.id.catalog);
        mycatalog = findViewById(R.id.mycatalog);
        installer = findViewById(R.id.installer);
        user_profile = findViewById(R.id.user_profile);
        frameTransition = findViewById(R.id.Frame_transition);


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
        mycatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCatalogFragment myCatalogFragment = new MyCatalogFragment();
                setNewFragment(myCatalogFragment);
            }
        });
        catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatalogFragment catalogFragment = new CatalogFragment();
                setNewFragment(catalogFragment);
            }
        });
//        closing_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setExitAlertDialog();
//            }
//        });


    }

    public void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Frame_transition, fragment);
        ft.commit();

    }
//    public void setExitAlertDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
//        builder.setTitle("Внимание!");
//        builder.setMessage("Вы действительно хотите закрыть приложение?");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Конечно", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

}