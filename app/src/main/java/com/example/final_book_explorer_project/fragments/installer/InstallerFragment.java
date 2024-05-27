package com.example.final_book_explorer_project.fragments.installer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity3;
import com.example.final_book_explorer_project.fragments.mycatalog.Book;
import com.example.final_book_explorer_project.handlers.SharedPreferencesHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class InstallerFragment extends Fragment {
    public Button start_conductor;
    private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;
    public static int page_count;
    public static List<Book> bookList = new ArrayList<>();
    public static String fileContent;
    public static List<String> fileName_list = new ArrayList<String>();
    public static String fileName; // Название открытого файла

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
                fileName = getFileNameFromUri(uri); // Получаем название файла из URI
                readTextFile(uri);
            }
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (fileName == null) {
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }

    private void readTextFile(Uri uri) {
        try (InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            fileContent = stringBuilder.toString();

            Log.d("InstallerFragment", "File Content: " + fileContent);
            Log.d("InstallerFragment", "File Name: " + fileName);

            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity3.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void maker_book_in_catalog() {
        if (!fileName_list.contains(fileName)) {
            bookList.add(new Book(fileName, "Author"));
            fileName_list.add(fileName);
        }
        SharedPreferencesHelper.saveArrayList(getContext(), fileName_list); //TODO

    }
}
