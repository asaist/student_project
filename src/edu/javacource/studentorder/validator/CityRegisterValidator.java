package edu.javacource.studentorder.validator;

import edu.javacource.studentorder.domain.AnswerCityRegister;
import edu.javacource.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.domain.StudentOrder;
import edu.javacource.studentorder.exception.CityRegisterException;

public class CityRegisterValidator {
    public String hostName;
    protected int port;
    private String login;
    String password;

    private FakeCityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband());
            CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife());
            CityRegisterCheckerResponse cans = personChecker.checkPerson(so.getChild());
        }catch (CityRegisterException ex){
            ex.printStackTrace();
        }
        AnswerCityRegister ans =  new AnswerCityRegister();
        return ans;
    }
}
