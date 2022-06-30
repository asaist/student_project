package edu.javacource.studentorder.validator;

import edu.javacource.studentorder.domain.Child;
import edu.javacource.studentorder.domain.Person;
import edu.javacource.studentorder.domain.register.AnswerCityRegister;
import edu.javacource.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javacource.studentorder.domain.register.CityRegisterResponse;
import edu.javacource.studentorder.domain.StudentOrder;
import edu.javacource.studentorder.exception.CityRegisterException;
import edu.javacource.studentorder.validator.register.FakeCityRegisterChecker;

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
       AnswerCityRegister ans = new AnswerCityRegister();

            ans.addItem(checkPerson(so.getHusband()));
            ans.addItem(checkPerson(so.getWife()));
            for (Child child: so.getChildren()){
                ans.addItem(checkPerson(child));
            }
        return ans;
    }
    private AnswerCityRegisterItem checkPerson(Person person){

        try {
            CityRegisterResponse cans = personChecker.checkPerson(person);
        }catch (CityRegisterException ex){
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
