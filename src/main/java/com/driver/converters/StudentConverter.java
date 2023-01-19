package com.driver.converters;

import com.driver.RequestDTO.StudentRequestDTO;
import com.driver.models.Student;
import lombok.Data;

@Data
public class StudentConverter {

    public static Student convertStudentDTOtoEntity(StudentRequestDTO studentRequestDTO)
    {
        Student newStudent= Student.builder()
                .emailId(studentRequestDTO.getEmailId())
                .name(studentRequestDTO.getName())
                .age(studentRequestDTO.getAge())
                .country(studentRequestDTO.getCountry()).build();
        return newStudent;
    }
}
