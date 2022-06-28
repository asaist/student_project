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
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }
    public void checkAll(){
        while (true) {
            StudentOrder [] so = readStudentOrders();

            if (so == null){
                break;
            }
            System.out.println("Finish");
            AnswerCityRegister cityAnswer = checkCityRegister(so[0]);
            if (!cityAnswer.isSuccess()){
                break;
            }

            AnswerWedding answerWedding = checkWedding(so[0]);
            AnswerChildren answerChildren = checkChildren(so[0]);
            AnswerStudent answerStudent = checkStudent(so[0]);
            sendMail(so[0]);
        }

        System.out.println("Finish 2");
    }

    public StudentOrder [] readStudentOrders(){

        StudentOrder [] soArray = new StudentOrder[3];

        for (int i=0;i<soArray.length;i++){
            soArray[i] = SaveStudentOrder.buildStudentOrder(i);
        }

        return soArray;
    }
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterVal.checkCityRegister(so);
    }
    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);

    }
    public AnswerChildren checkChildren(StudentOrder so){
        return childrenVal.checkChildren(so);
    }
    public AnswerStudent checkStudent(StudentOrder so){
        return studentVal.checkStudent(so);
    }
    public void sendMail(StudentOrder so)
    {
        mailSender.sendMail(so);
    }
}
