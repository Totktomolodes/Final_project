package com.example.final_book_explorer_project.fragments.mycatalog;

import static android.content.Intent.getIntent;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;
import com.example.final_book_explorer_project.fragments.mycatalog.Book;
import com.example.final_book_explorer_project.fragments.mycatalog.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        bookList = InstallerFragment.bookList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


//        bookList = new ArrayList<>();
        // Заполнение списка книг (пример)
//        bookList.add(new Book("Title1", "Author1"));
//        bookList.add(new Book("Title2", "Author2"));

        bookAdapter = new BookAdapter(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                // Открытие фрагмента редактирования
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                EditBookFragment editBookFragment = EditBookFragment.newInstance(book);
                transaction.replace(R.id.Frame_transition, editBookFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        recyclerView.setAdapter(bookAdapter);

        return view;
    }
}



