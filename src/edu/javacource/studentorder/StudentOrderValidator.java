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
            StudentOrder [] soArray = readStudentOrders();

           for (StudentOrder so :soArray) {
               checkOneOrder(so);
           }
    }

    public StudentOrder [] readStudentOrders(){

        StudentOrder [] soArray = new StudentOrder[1];

        for (int i=0;i<soArray.length;i++){
            soArray[i] = SaveStudentOrder.buildStudentOrder(i);
        }

        return soArray;
    }

    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
        //    AnswerWedding answerWedding = checkWedding(so[0]);
//            AnswerChildren answerChildren = checkChildren(so[0]);
//            AnswerStudent answerStudent = checkStudent(so[0]);
//            sendMail(so[0]);
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
