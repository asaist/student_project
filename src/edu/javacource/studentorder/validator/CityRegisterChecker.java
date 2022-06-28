package edu.javacource.studentorder.validator;

import edu.javacource.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {
     CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException;
}
