package com.example.final_book_explorer_project.fragments.mycatalog;
import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    private int index;

    public Book(String title, String author, int index, String text) {
        this.title = title;
        this.index = index;
        this.author = author;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}



