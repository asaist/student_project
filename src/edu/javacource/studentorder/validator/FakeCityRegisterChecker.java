package edu.javacource.studentorder.validator;

import edu.javacource.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.exception.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
