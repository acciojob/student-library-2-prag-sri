package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository1;

    public void createBook(Book book){
        int authorId = book.getAuthor().getId();

        Author author =  authorRepository1.findById(authorId).get();
        author.getBooksWritten().add(book);

        book.setAuthor(author);

        bookRepository2.save(book);
        authorRepository1.save(author);

    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = new ArrayList<>(); //find the elements of the list by yourself

        if(genre==null && author==null)
        {
            List<Book> b4= bookRepository2.findByAvailability(available);
            books.addAll(b4);
        }
        else if(genre==null)
        {
            List<Book> b1= bookRepository2.findBooksByAuthor(author,available);
            books.addAll(b1);
        }
        else if(author==null)
        {
            List<Book> b2= bookRepository2.findBooksByGenre(genre,available);
            books.addAll(b2);
        }
        else if(genre!=null && author!=null)
        {
            List<Book> b3= bookRepository2.findBooksByGenreAuthor(genre,author,available);
            books.addAll(b3);
        }

        return books;
    }
}
