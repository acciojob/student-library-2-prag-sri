package com.driver.RequestDTO;

import com.driver.models.Author;
import com.driver.models.Card;
import com.driver.models.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
public class BookRequestDTO {
    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int authorId;

}
