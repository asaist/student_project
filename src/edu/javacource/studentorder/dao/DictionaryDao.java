package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.domain.CountryArea;
import edu.javacource.studentorder.domain.PassportOffice;
import edu.javacource.studentorder.domain.RegisterOffice;
import edu.javacource.studentorder.domain.Street;
import edu.javacource.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices(String areaId) throws DaoException;
    List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException;
    List<CountryArea> findAreas(String areaId) throws DaoException;
}
