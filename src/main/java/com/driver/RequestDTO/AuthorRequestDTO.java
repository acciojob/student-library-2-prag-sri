package com.driver.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
public class AuthorRequestDTO {
    private String name;

    @Column(unique = true)
    private String email;

    private int age;
    private String country;
}
