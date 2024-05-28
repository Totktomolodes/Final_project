package com.example.final_book_explorer_project.fragments.mycatalog;

import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.fileName;
import static com.example.final_book_explorer_project.handlers.SharedPreferencesHelper.getBookList;

import android.content.Context;
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

import java.util.List;

public class MyCatalogFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private List<Book> NewBookList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_catalog, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        bookList = InstallerFragment.bookList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Shared_catalog_Viewer(getActivity());

        bookAdapter = new BookAdapter(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
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
    public void Shared_catalog_Viewer(Context context){ //TODO
        bookList = getBookList(context);



    }
}



