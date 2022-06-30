package edu.javacource.studentorder.validator.register;

import edu.javacource.studentorder.domain.register.CityRegisterResponse;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {
     CityRegisterResponse checkPerson(Person person) throws CityRegisterException;
}
