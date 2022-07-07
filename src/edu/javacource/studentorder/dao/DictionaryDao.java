package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.domain.Street;
import edu.javacource.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
}
