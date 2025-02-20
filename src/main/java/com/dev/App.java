package com.dev;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        int id = new Random().nextInt(100000);
        Student student = new Student(id, "dove " + id, "sika " + id);
        StudentDAO studentDAO = new StudentDAOImpl();
      
        try {
            studentDAO.addStudent(student);
            System.out.println("Student added successfully");

            System.out.println("All students: ");
            for (Student stud : studentDAO.getAllStudents()) {
                System.out.println(stud);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
