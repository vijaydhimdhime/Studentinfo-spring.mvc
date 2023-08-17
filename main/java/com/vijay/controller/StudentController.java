package com.vijay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vijay.entity.Student;
import com.vijay.entity.Subjects;
import com.vijay.service.StudentService;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    public String createStudent(@RequestBody Student std) {
        System.out.println("request received to saveStudent: "+std);

        if (studentService.saveStudent( std))
            return "student saved successfully!!!";
        else
            return "student not saved...";

    }
     @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") int id) {

        System.out.println("request received to getStudent of id: "+ id);

        Student retrivedStudent = studentService.getStudent(id);
         return retrivedStudent;
    }

    @GetMapping("/students/lists")
    public List<Student> getStudents() {

       List<Student> studentList = studentService.getStudents();


        return studentList;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
    String msg = null;
        System.out.println("request received to  delete Student of id: "+ id);

        if(studentService.deleteStudent(id)){
            msg = "student deleted succseefully";
        }
        else {
            msg ="something is wrong";
        }
        return msg;
    }
    @PutMapping("/students/update")
    public Student updateStudent(@RequestBody Student std){
        System.out.println("request received to update student :"+std);
        return studentService.updateStudent(std);

    }

    @GetMapping("/subjects")
    public List<Subjects> getSubjects(){
        System.out.println("request received to subjects");
        return studentService.getSubjects();

    }

    @GetMapping("/students/{id}/subjects")
    public List<Subjects> getSubjectsById(@PathVariable("id") int id){
        System.out.println("request received to get subjects by id :"+id);


        return studentService.getSubjectsById(id);



    }

}