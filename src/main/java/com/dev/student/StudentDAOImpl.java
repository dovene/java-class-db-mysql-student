package com.dev.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dev.config.DatabaseConfig;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) throws ClassNotFoundException, SQLException {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Connection connection = databaseConfig.getConnection();
        String insertionQuery = "INSERT INTO student (id, firstName, lastName) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getFirstName());
        preparedStatement.setString(3, student.getLastName());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }


    @Override
    public ArrayList<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = new DatabaseConfig().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setFirstName(resultSet.getString("firstName"));
            student.setLastName(resultSet.getString("lastName"));
            students.add(student);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return students;
    }
    
}
