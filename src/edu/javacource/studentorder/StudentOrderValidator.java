package edu.javacource.studentorder;

import edu.javacource.studentorder.domain.*;
import edu.javacource.studentorder.mail.MailSender;
import edu.javacource.studentorder.validator.ChildrenValidator;
import edu.javacource.studentorder.validator.CityRegisterValidator;
import edu.javacource.studentorder.validator.StudentValidator;
import edu.javacource.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        checkAll();
    }
    void checkAll(){
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

    public StudentOrder readStudentOrder(){
        StudentOrder so = new StudentOrder();
        SaveStudentOrder.buildStudentOrder();
        return so;
    }
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterVal.checkCityRegister(so);
    }
    public AnswerWedding checkWedding(StudentOrder so){
        return WeddingValidator.checkWedding(so);
    }
    public AnswerChildren checkChildren(StudentOrder so){
        return ChildrenValidator.checkChildren(so);
    }
    public AnswerStudent checkStudent(StudentOrder so){
        return StudentValidator.checkStudent(so);
    }
    public void sendMail(StudentOrder so)
    {
        new MailSender().sendMail(so);
    }
}
