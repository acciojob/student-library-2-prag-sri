package com.driver.controller;

import com.driver.RequestDTO.BookRequestDTO;
import com.driver.models.Book;
import com.driver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Add required annotations
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService2;

    //Write createBook API with required annotations
    @PostMapping("/create_book")
    public ResponseEntity<String> createBook(@RequestBody()Book book)
    {
        String result= bookService2.create(book);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/get_books")
    //Add required annotations
    public ResponseEntity getBooks(@RequestParam(value = "genre", required = false) String genre,
                                   @RequestParam(value = "available", required = false, defaultValue = "false") boolean available,
                                   @RequestParam(value = "author", required = false) String author){

        List<Book> bookList = bookService2.getBooks(genre,available,author); //find the elements of the list by yourself

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }
}
