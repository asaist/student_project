package validator;

import domain.CityRegisterCheckerResponse;
import domain.Person;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    public CityRegisterCheckerResponse checkPerson(Person person){
        return null;
    }
}
