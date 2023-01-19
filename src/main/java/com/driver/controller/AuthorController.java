package com.driver.controller;

import com.driver.RequestDTO.AuthorRequestDTO;
import com.driver.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Add required annotations
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    //Write createAuthor API with required annotations
    @PostMapping("create_author")
    public ResponseEntity<String> createAuthor(@RequestBody() AuthorRequestDTO authorRequestDTO)
    {
        String result= authorService.create(authorRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}