package edu.javacource.studentorder;

import edu.javacource.studentorder.domain.*;
import edu.javacource.studentorder.mail.MailSender;
import edu.javacource.studentorder.validator.ChildrenValidator;
import edu.javacource.studentorder.validator.CityRegisterValidator;
import edu.javacource.studentorder.validator.StudentValidator;
import edu.javacource.studentorder.validator.WeddingValidator;

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
        SaveStudentOrder.buildStudentOrder();
        return so;
    }
    static AnswerCityRegister checkCityRegister(StudentOrder so){
        CityRegisterValidator crv1 = new CityRegisterValidator();
        crv1.hostName = "Host1";


        CityRegisterValidator crv2 = new CityRegisterValidator();
        crv2.hostName = "Host2";


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
    static void sendMail(StudentOrder so)
    {
        new MailSender().sendMail(so);
    }
}
