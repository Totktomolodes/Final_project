package com.example.final_book_explorer_project.interfaces;

public interface Paginator <T> {

    T GetPreviousPage();

    T GetCurrentPage();

    T GetNextPage();

    T IsMaxPage();

    T IsMinPage();

    T GetLenPage();



}
