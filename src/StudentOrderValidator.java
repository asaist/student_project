import domain.*;
import mail.MailSender;
import validator.ChildrenValidator;
import validator.CityRegisterValidator;
import validator.StudentValidator;
import validator.WeddingValidator;

public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }
    static void checkAll(){
        while (true) {
            StudentOrder so = readStudentOrder();
            System.out.println("Start");
            if (so == null){
                break;
            }
            System.out.println("Finish");
            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.isSuccess()){
                break;
            }

            AnswerWedding answerWedding = checkWedding(so);
            AnswerChildren answerChildren = checkChildren(so);
            AnswerStudent answerStudent = checkStudent(so);
            sendMail(so);
        }
        System.out.println("Finish 2");
    }

    static StudentOrder readStudentOrder(){
        StudentOrder so = new StudentOrder();
        return so;
    }
    static AnswerCityRegister checkCityRegister(StudentOrder so){
        CityRegisterValidator crv1 = new CityRegisterValidator();
        crv1.setHostName("Host1");
        crv1.setLogin("login1");
        crv1.setPassword("password1");
        CityRegisterValidator crv2 = new CityRegisterValidator();
        crv2.setHostName("Host2");
        crv2.setLogin("login2");
        crv2.setPassword("password2");
        AnswerCityRegister ans1 = crv1.checkCityRegister(so);
        AnswerCityRegister ans2 = crv2.checkCityRegister(so);
        return ans1;
    }
    static AnswerWedding checkWedding(StudentOrder so){
        return WeddingValidator.checkWedding(so);
    }
    static AnswerChildren checkChildren(StudentOrder so){
        return ChildrenValidator.checkChildren(so);
    }
    static AnswerStudent checkStudent(StudentOrder so){
        return StudentValidator.checkStudent(so);
    }
    static void sendMail(StudentOrder so){
        new MailSender().sendMail(so);
    }
}
