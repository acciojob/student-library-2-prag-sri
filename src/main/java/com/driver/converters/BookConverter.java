package com.driver.converters;

import com.driver.RequestDTO.BookRequestDTO;
import com.driver.models.Book;

public class BookConverter {

    public static Book convertBookDTOtoEntity(BookRequestDTO bookRequestDTO)
    {
        Book book= Book.builder()
                      .name(bookRequestDTO.getName())
                      .genre(bookRequestDTO.getGenre()).build();
        return book;
    }
}
