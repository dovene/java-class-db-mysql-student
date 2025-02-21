package com.dev.student;

import java.sql.SQLException;
import java.util.ArrayList;


public interface StudentDAO {
     ArrayList<Student> getAllStudents() throws ClassNotFoundException, SQLException;
     boolean addStudent(Student client) throws ClassNotFoundException, SQLException;
}
