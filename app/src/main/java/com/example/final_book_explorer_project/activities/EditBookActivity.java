package com.example.final_book_explorer_project.activities;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_book_explorer_project.R;

public class EditBookActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String description = intent.getStringExtra("description");
        int position = intent.getIntExtra("position", -1);

        titleEditText.setText(title);
        authorEditText.setText(author);
        descriptionEditText.setText(description);

        saveButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", titleEditText.getText().toString());
            resultIntent.putExtra("author", authorEditText.getText().toString());
            resultIntent.putExtra("description", descriptionEditText.getText().toString());
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}


