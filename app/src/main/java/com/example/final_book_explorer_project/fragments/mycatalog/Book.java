package com.example.final_book_explorer_project.fragments.mycatalog;
public class Book {
    private String title;
    private String author;
    private String description;

    // Конструктор
    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    // Геттеры и сеттеры
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
