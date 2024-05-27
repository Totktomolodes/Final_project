package com.example.final_book_explorer_project.fragments.mycatalog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_book_explorer_project.R;

public class EditBookFragment extends Fragment {
    private static final String ARG_BOOK = "book";
    private Book book;
    private EditText titleEditText;
    private EditText authorEditText;
    private Button saveButton;

    public static EditBookFragment newInstance(Book book) {
        EditBookFragment fragment = new EditBookFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_book, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        authorEditText = view.findViewById(R.id.authorEditText);
        saveButton = view.findViewById(R.id.saveButton);

        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable(ARG_BOOK);
            if (book != null) {
                titleEditText.setText(book.getTitle());
                authorEditText.setText(book.getAuthor());
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book != null) {
                    book.setTitle(titleEditText.getText().toString());
                    book.setAuthor(authorEditText.getText().toString());
                    // Обновление данных в списке книг
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }
}

