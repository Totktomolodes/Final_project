package com.example.final_book_explorer_project.fragments.mycatalog;

import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.bookList;
import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.fileName_list;

import android.content.Context;
import android.content.Intent;
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
import com.example.final_book_explorer_project.activities.MainActivity3;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;

public class EditBookFragment extends Fragment {
    private static final String ARG_BOOK = "book";
    private Book book;
    private EditText titleEditText;
    private EditText authorEditText;
    private Button saveButton, switching_to_activity, del_button;


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
        View view = inflater.inflate(R.layout.edit_book, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        authorEditText = view.findViewById(R.id.authorEditText);
        saveButton = view.findViewById(R.id.saveButton);
        switching_to_activity = view.findViewById(R.id.switching_to_activity);
        del_button = view.findViewById(R.id.del_button);

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
                    InstallerFragment.fileName_list.add(titleEditText.getText().toString());
                    book.setTitle(titleEditText.getText().toString());
                    book.setAuthor(authorEditText.getText().toString());
                    getParentFragmentManager().popBackStack();
                }
            }
        });
        switching_to_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
                startActivity(new Intent(getActivity(), MainActivity3.class));
            }
        });
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName_list.remove(book.getTitle());
                bookList.remove(book);
                getParentFragmentManager().popBackStack();
            }
        });




        return view;
    }
}

