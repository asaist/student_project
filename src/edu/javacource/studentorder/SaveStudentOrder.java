package edu.javacource.studentorder;

import edu.javacource.studentorder.domain.Adult;
import edu.javacource.studentorder.domain.StudentOrder;

public class SaveStudentOrder {
    public static void main(String[] args) {


        buildStudentOrder(5);
//        StudentOrder so1 = new StudentOrder();
//        long ans1 = saveStudentOrder(so1);
//        System.out.println(ans1);


    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id){
        StudentOrder so = new StudentOrder();
        so.setId(id);
        StudentOrder so1 = so;
        printStudentOrder(so1);
        Adult husband = new Adult("Васильев","Андрей","Петрович", null);


        return so;
    }

    static void printStudentOrder(StudentOrder so){
        System.out.println(so.getId());
    }


}
