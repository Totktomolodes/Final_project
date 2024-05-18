package com.example.fb2reader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.final_book_explorer_project.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InstallerFragment extends Fragment {

    private static final int PICK_FILE_REQUEST = 1;
    private TextView tvContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnOpenFile = view.findViewById(R.id.btn_open_file);
        tvContent = view.findViewById(R.id.tv_content);

        btnOpenFile.setOnClickListener(v -> openFileChooser());

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            readFB2File(uri);
        }
    }

    private void readFB2File(Uri uri) {
        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            inputStream.close();
            parseFB2Content(sb.toString());
        } catch (Exception e) {
            Log.e("FB2Reader", "Error reading file", e);
        }
    }

    private void parseFB2Content(String content) {
        Document doc = Jsoup.parse(content);
        Elements bodyElements = doc.select("body");
        StringBuilder parsedContent = new StringBuilder();
        for (org.jsoup.nodes.Element element : bodyElements) {
            parsedContent.append(element.text()).append("\n");
        }
        tvContent.setText(parsedContent.toString());
    }
}
