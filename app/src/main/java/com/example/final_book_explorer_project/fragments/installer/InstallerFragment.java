package com.example.final_book_explorer_project.fragments.installer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_book_explorer_project.R;
import android.content.Intent;


public class InstallerFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_installer, container, false);
    }


    public void open_doc(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimetypes = {"application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);


        ActivityResultLauncher<Intent> getContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uriPdf = result.getData().getData();
                        System.out.println(uriPdf);

                    }
                }
        );
        getContent.launch(intent);

// In your button click or similar event
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
//        getContent.launch(intent);

    }
}