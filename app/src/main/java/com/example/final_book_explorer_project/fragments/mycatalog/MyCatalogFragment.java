package com.example.final_book_explorer_project.fragments.mycatalog;

import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.bookList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.mycatalog.Book;
import com.example.final_book_explorer_project.fragments.mycatalog.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCatalogFragment extends Fragment {

    private static final int EDIT_BOOK_REQUEST = 1;

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;


    public static MyCatalogFragment newInstance(String param1, String param2) {
        MyCatalogFragment fragment = new MyCatalogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_catalog, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bookAdapter = new BookAdapter(bookList, requireActivity());
        recyclerView.setAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_BOOK_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            String title = data.getStringExtra("title");
            String author = data.getStringExtra("author");
            String description = data.getStringExtra("description");
            if (position != -1) {
                bookAdapter.updateBook(position, title, author, description);
            }
        }
    }
}


