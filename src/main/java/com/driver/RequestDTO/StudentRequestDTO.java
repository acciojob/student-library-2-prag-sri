package com.driver.RequestDTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StudentRequestDTO {
    @Column(unique = true)
    private String emailId;
    private String name;
    private int age; // in case we want to check on the basis of age while issuing
    private String country;

}
