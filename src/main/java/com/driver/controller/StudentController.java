package com.driver.controller;

import com.driver.RequestDTO.StudentRequestDTO;
import com.driver.models.Student;
import com.driver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get_student_by_email")
    //Add required annotations
    public ResponseEntity<Student> getStudentByEmail(@RequestParam("email") String email){
        Student student= studentService.getDetailsByEmail(email);
        //return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("get_student_by_id")
    //Add required annotations
    public ResponseEntity<Student> getStudentById(@RequestParam("id") int id){
        Student student= studentService.getDetailsById(id);
//        return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("/create_student")
    public ResponseEntity createStudent(@RequestBody() StudentRequestDTO studentRequestDTO){
        studentService.createStudent(studentRequestDTO);
        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    public ResponseEntity updateStudent(@RequestBody Student student){

        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete_student")
    //Add required annotations
    public ResponseEntity deleteStudent(@RequestParam("id") int id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }

}
