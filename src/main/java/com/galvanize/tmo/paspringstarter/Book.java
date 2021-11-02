package com.galvanize.tmo.paspringstarter;

public class Book {
    private int id;
    private String author;
    private String title;
    private int yearPublished;

    public Book(int id, String author, String title, int yearPublished) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    
}
