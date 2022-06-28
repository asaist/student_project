package edu.javacource.studentorder.domain;

public class StudentOrder {
    private long id;
    private Adult husband;
    private Adult wife;
    private Child child;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
