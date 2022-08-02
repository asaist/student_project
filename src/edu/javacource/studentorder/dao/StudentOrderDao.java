package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.domain.StudentOrder;
import edu.javacource.studentorder.exception.DaoException;

import java.sql.SQLException;

public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder so) throws DaoException, SQLException;
}
