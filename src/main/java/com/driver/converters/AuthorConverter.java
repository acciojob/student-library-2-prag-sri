package com.driver.converters;

import com.driver.RequestDTO.AuthorRequestDTO;
import com.driver.models.Author;

public class AuthorConverter {

    public static Author convertAuthorDTOtoEntity(AuthorRequestDTO authorRequestDTO)
    {
        Author author= Author.builder()
                .name(authorRequestDTO.getName())
                .email(authorRequestDTO.getEmail())
                .age(authorRequestDTO.getAge())
                .country(authorRequestDTO.getCountry()).build();
        return author;
    }
}
