package com.example.final_book_explorer_project.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import com.example.final_book_explorer_project.fragments.mycatalog.Book;

public class SharedPreferencesHelper {

    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_BOOK_LIST = "my_book_list";

    public static void saveBookList(Context context, List<Book> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_BOOK_LIST, json);
        editor.apply();
    }

    public static List<Book> getBookList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_BOOK_LIST, null);
        Type type = new TypeToken<List<Book>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
