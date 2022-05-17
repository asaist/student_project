package edu.javacource.studentorder;

import edu.javacource.studentorder.domain.other.Adult;
import edu.javacource.studentorder.domain.Child;
import edu.javacource.studentorder.domain.StudentOrder;

public class SaveStudentOrder {
    public static void main(String[] args) {


        buildStudentOrder();
//        StudentOrder so1 = new StudentOrder();
//        long ans1 = saveStudentOrder(so1);
//        System.out.println(ans1);


    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    public static StudentOrder buildStudentOrder(){
        StudentOrder so = new StudentOrder();
        Adult husband = new Adult();
        husband.setSurName("Andrey");
        husband.setGivenName("Petrov");
        husband.setPassportNumber("123456");
        so.setHusband(husband);
        String ans = husband.getPersonString();
        System.out.println(ans);
        return so;
    }


}
