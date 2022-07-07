package edu.javacource.studentorder.domain;

public class Street {
    private Long streetCode;
    private String street;

    public Street() {
    }

    public Street(Long streetCode, String street) {
        this.streetCode = streetCode;
        this.street = street;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
