package com.galvanize.tmo.paspringstarter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Object;
import org.springframework.util.MimeType;
import javax.swing.text.Utilities;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LibraryController {
    private int currentId= 0;

    // Book book1 = new Book(10, "Mustafa", "title1", 1993);
    // Book book2 = new Book(11, "Ramadan", "title2", 1994);
    // Book book3 = new Book(12, "Author3", "title3", 1995);
    private ArrayList<Book> allBooks;

    public LibraryController() {
        allBooks = new ArrayList<>();
    }
    
    
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
    public Book postBook(@RequestBody String input) {
        StringBuilder sb = new StringBuilder();
        for (char c: input.toCharArray()) {
            if (c != '{' && c!= '}') {
                sb.append(c);
            }
        }

        String newString = sb.toString();
        String[] array = newString.split(",");
        
        String author = array[0].split(":")[1];
        StringBuilder newauthor = new StringBuilder();
        for (char c: author.toCharArray()) {
            if (c != '"') {
                newauthor.append(c);
            }
        }

        String title = array[1].split(":")[1];
        StringBuilder newtitle = new StringBuilder();
        for (char c: title.toCharArray()) {
            if (c != '"') {
                newtitle.append(c);
            }
        }

        String yearPublished = array[2].split(":")[1];
        StringBuilder newyear = new StringBuilder();
        for (char c: yearPublished.toCharArray()) {
            if (c != '"') {
                newyear.append(c);
            }
        }

        currentId +=1;

        // int intYear = Integer.parseInt(year.toString());
        Book newBook = new Book(currentId, newauthor.toString().trim(), newtitle.toString().trim(), Integer.parseInt(newyear.toString().trim()));
        System.out.println(newBook.getAuthor());
        System.out.println(newBook.getTitle());
        System.out.println(newBook.getYearPublished());

        // System.out.println(newBook.getYearPublished());
        allBooks.add(newBook);
        return newBook;






        // currentId += 1;
        // Book newBook = new Book(currentId, author, title, yearPublished);
        // allBooks.add(newBook);
        // return newBook;
    }
    

    // Need to alphabataize the books
    @GetMapping(path= "/api/books", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public HashMap<String, ArrayList<Book>> getBooks() {
        // allBooks = loadSampleData();
        
        

        allBooks.sort((a,b)->a.getTitle().compareTo(b.getTitle()));
        // JsonArray copy = new JsonArray(allBooks);
        // JSONObject finalJsonObj = new JSONObject();
        String keyBooks = "books";

        HashMap<String, ArrayList<Book>> map = new HashMap<>();
        map.put(keyBooks, allBooks);

        return map;

        // finalJsonObj.put(key, copy);
        // // ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(finalJsonObj, HttpStatus.OK);
        // ResponseEntity<JSONObject> responseEntity;
        // responseEntity = new ResponseEntity<>(finalJsonObj, HttpStatus.OK);
        // return responseEntity;
        // return finalJsonObj;

        

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
