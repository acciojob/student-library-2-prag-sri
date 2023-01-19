package com.driver.services;

import com.driver.RequestDTO.BookRequestDTO;
import com.driver.converters.BookConverter;
import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository;

    public String create(BookRequestDTO bookRequestDTO){
        Book newbook= BookConverter.convertBookDTOtoEntity(bookRequestDTO);

        int authorId= bookRequestDTO.getAuthorId();
        Author author= authorRepository.findById(authorId).get();
        newbook.setAuthor(author);

        List<Book> bookList= author.getBooksWritten();
        bookList.add(newbook);
        author.setBooksWritten(bookList);
        bookRepository2.save(newbook);

        authorRepository.save(author);

        return "Success";
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = null; //find the elements of the list by yourself
        return books;
    }
}
