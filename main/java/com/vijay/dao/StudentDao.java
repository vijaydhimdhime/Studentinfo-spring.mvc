package com.vijay.dao;

import java.util.List;

import com.vijay.entity.Student;
import com.vijay.entity.Subjects;

public interface StudentDao {
    public boolean saveStudent(Student std);
    public Student getStudent(int id);
    public List<Student> getStudents();
    public boolean deleteStudent(int id);
    public Student updateStudent(Student std);
    public List<Subjects> getSubjects();
  

}
