package com.driver.services;

import com.driver.models.Author;
import com.driver.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository1;

    public String create(Author author){
        //Author author= AuthorConverter.convertAuthorDTOtoEntity(authorRequestDTO);
        authorRepository1.save(author);
        return "Success";
    }
}
