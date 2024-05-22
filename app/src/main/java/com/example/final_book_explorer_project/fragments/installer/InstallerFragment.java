package com.example.final_book_explorer_project.fragments.installer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity;
import com.example.final_book_explorer_project.activities.MainActivity3;
import com.example.final_book_explorer_project.activities.MainActivity2;
import com.example.final_book_explorer_project.fragments.user_profile.Settings_fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class InstallerFragment extends Fragment {
    public Button start_conductor;
    private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;
    public static int page_count;
    public static String fileContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installer, container, false);
    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            view.findViewById(R.id.start_conductor).setOnClickListener(v -> openFilePicker());


    }
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, REQUEST_CODE_OPEN_DOCUMENT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_DOCUMENT && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                readTextFile(uri);
            }
        }
    }
    private void readTextFile(Uri uri) {
        try (InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            String fileContent = stringBuilder.toString();
            int page_count = fileContent.length() / 5000;


            // Handle the file content here
            Log.d("MainActivity", "File Content: " + fileContent); //fafafafafafafafa TODO

            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity3.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
