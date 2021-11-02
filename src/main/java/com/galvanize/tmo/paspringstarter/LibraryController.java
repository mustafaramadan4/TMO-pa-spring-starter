package com.galvanize.tmo.paspringstarter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LibraryController {
    private int currentId= 0;

    // Book book1 = new Book(10, "Mustafa", "title1", 1993);
    // Book book2 = new Book(11, "Ramadan", "title2", 1994);
    // Book book3 = new Book(12, "Author3", "title3", 1995);
    private ArrayList<Book> allBooks = new ArrayList<>();
    
    
    // public ArrayList loadSampleData() {
    //     allBooks.add(book1);
    //     allBooks.add(book2);
    //     allBooks.add(book3);
    //     return allBooks;
    // }
    
    

    @GetMapping("/health")
    public void health() {
    }

    // Need to figure out what im doing with the ids
    @PostMapping("/api/books") 
    @ResponseStatus(code = HttpStatus.CREATED)
    public Book postBook(String author, String title, int yearPublished) {
        currentId +=1;
        Book newBook = new Book(currentId, author, title, yearPublished);
        allBooks.add(newBook);
        return newBook;
    }

    // Need to alphabataize the books
    @GetMapping("/api/books")
    @ResponseStatus(code = HttpStatus.OK)
    public ArrayList<Book> getBooks() {
        // allBooks = loadSampleData();
        allBooks.sort((a,b)->a.getTitle().compareTo(b.getTitle()));
        return allBooks;
    }

    // Need to alphabataize the books
    @DeleteMapping("/api/books")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ArrayList<Book> deleteAllBooks() {
        allBooks.clear();
        currentId = 0;
        return allBooks;
    }
    
}
