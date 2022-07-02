package validator;

import domain.AnswerCityRegister;
import domain.StudentOrder;

public class CityRegisterValidator {
    private String hostName;
    private int port;

    private String login;
    private String password;
    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so){

        personChecker.checkPerson(so.getHusband());
        personChecker.checkPerson(so.getWife());
        personChecker.checkPerson(so.getChild());



        AnswerCityRegister ans =  new AnswerCityRegister();

        return ans;
    }


}
