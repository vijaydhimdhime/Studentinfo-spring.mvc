package com.vijay.dao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vijay.entity.Student;
import com.vijay.entity.Subjects;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{
    @Autowired
    SessionFactory factory;

    @Override
    public boolean saveStudent(Student std2) {
         boolean result = false;
         try {
             Session session = factory.openSession();
             Transaction txn = session.beginTransaction();
             session.save(std2);
             txn.commit();
             session.close();
             result= true;
         }
         catch (Exception e){
             System.out.println("Error occurred while storing the student :"+e.getMessage());
         }
         return result;
    }

    @Override
    public Student getStudent(int id) {
        Student std = null;
        Session session = factory .openSession();
        Transaction transaction = session.beginTransaction();
        std = session.get(Student.class,id);
        transaction.commit();
        session.close();
        return std;
    }

    @Override
    public List<Student> getStudents() {
        List<Student>  students = null;
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Student> query = session.createQuery("from Student");
        students = query.list();
        transaction.commit();
        session.close();
        return students;

    }

    @Override
    public boolean deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Student retrivedstd = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            retrivedstd = session.get(Student.class,id);

           // if (retrivedstd != null){
                session.delete(retrivedstd);
                result = true;

            tx.commit();
            session.close();

        }
        catch (HibernateException ex){
            System.out.println("exception occured:"+ex.getMessage());
        }
        return result;
    }

    @Override
    public Student updateStudent(Student std) {
        Session session = factory.openSession();
        Transaction txn = null;
        Student retrivedstd = null;
        boolean result = false;
        try {
            txn = session.beginTransaction();
            retrivedstd = session.get(Student.class,std.getId());
            if (retrivedstd!= null){
                System.out.println("found existing student.. Updating it!");
                retrivedstd.setRoll(std.getRoll());
                retrivedstd.setName(std.getName());
                retrivedstd.setAddress(std.getAddress());
                session.update(retrivedstd);
                System.out.println("Updated successfully!!");
            }
            else
            {
                System.out.println("Since student was not present - creating it!!");
                session.save(std);
            }
            txn.commit();

        } catch (HibernateException ex) {
            System.out.println("Unable to update student : "+ ex.getMessage());
        }

        return retrivedstd;
    }

    @Override
    public List<Subjects> getSubjects() {
        List<Subjects> subjects = null;
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Subjects> query = session.createQuery("from Subjects");
        subjects = query.list();
        transaction.commit();
        session.close();
        return subjects;
    }

	
   
}
