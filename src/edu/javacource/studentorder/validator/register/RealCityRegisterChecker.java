package edu.javacource.studentorder.validator.register;

import edu.javacource.studentorder.domain.register.CityRegisterResponse;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.exception.CityRegisterException;

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
