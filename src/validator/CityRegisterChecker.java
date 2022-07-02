package validator;

import domain.CityRegisterCheckerResponse;
import domain.Person;

public interface CityRegisterChecker {
     CityRegisterCheckerResponse checkPerson(Person person);
}
