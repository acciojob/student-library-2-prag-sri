package com.driver.services;

import com.driver.models.Card;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        for(Student student: studentRepository4.findAll())
        {
            String currEmail= student.getEmailId();
            if(currEmail.equals(email))
                return student;
        }
        return null;
    }

    public Student getDetailsById(int id){
        Student student = studentRepository4.findById(id).get();
        return student;
    }

    public void createStudent(Student newStudent){
        //Student newStudent= StudentConverter.convertStudentDTOtoEntity(studentRequestDTO);
        studentRepository4.save(newStudent);
        Card newCard= cardService4.createAndReturn(newStudent);

    }

    public void updateStudent(Student student){
        studentRepository4.updateStudentDetails(student);
    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        Student student= studentRepository4.findById(id).get();
        Card card= student.getCard();
        cardService4.deactivateCard(id);
        card.setStudent(null);
        student.setCard(null);
        studentRepository4.delete(student);


    }
}
