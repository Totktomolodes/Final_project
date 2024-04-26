package com.example.final_book_explorer_project.screen_handlers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ScreenHelper{
    // Заменяем начальный фрагмент (например, FirstFragment) при запуске активити
    // Метод для замены текущего фрагмента на другой



    public static<T extends Fragment> void replaceFragment(Class<T> fragment, FragmentManager fragmentManager) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //todo        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null); // Добавляем транзакцию в стек возврата
        fragmentTransaction.commit();

    }
}
