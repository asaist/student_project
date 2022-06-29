package edu.javacource.studentorder.domain;

import java.time.LocalDate;
import java.util.Date;

public class StudentOrder {
    private long studentOrderId;
    private Adult husband;
    private Adult wife;
    private Child child;

    private String marriageCertificateId;
    private LocalDate marriageDate;
    private String marriageOffice;
    private Address address;

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public String getMarriageOffice() {
        return marriageOffice;
    }

    public Address getAddress() {
        return address;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public void setMarriageOffice(String marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
