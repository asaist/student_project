package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.domain.PassportOffice;
import edu.javacource.studentorder.domain.Street;
import edu.javacource.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices(String pattern) throws DaoException;
}
