package edu.javacource.studentorder;

import edu.javacource.studentorder.domain.Address;
import edu.javacource.studentorder.domain.Adult;
import edu.javacource.studentorder.domain.Child;
import edu.javacource.studentorder.domain.StudentOrder;

import java.time.LocalDate;

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
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016,7,4));
        so.setMarriageOffice("Отдел ЗАГС");

        Address address = new Address("195000", "Заневский пр.", "12", "", "142");

        //Муж
        Adult husband = new Adult("Петров","Виктор","Сергеевич", LocalDate.of(1997,8,24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017,9,15));
        husband.setIssueDepartment("Отдел милиции №" + id);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        //Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998,3,12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018,4,5));
        wife.setIssueDepartment("Отдел милиции №" + id);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);

        //Ребенок
        Child child = new Child("Петрова","Ирина", "Викторовна", LocalDate.of(2018,6,29));
        child.setCertificateNumber("" + (300000 + id));
        child.setIssueDate(LocalDate.of(2018,7,19));
        child.setIssueDepartment("Отдел ЗАГС №" + id);
        child.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.setChild(child);
        
        return so;
    }

    static void printStudentOrder(StudentOrder so){
        System.out.println(so.getStudentOrderId());
    }


}
