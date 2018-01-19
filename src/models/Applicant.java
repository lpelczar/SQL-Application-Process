package models;

public class Applicant {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int applicationCode;

    public Applicant(String firstName, String lastName, String phoneNumber, String email, int applicationCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.applicationCode = applicationCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getApplicationCode() {
        return applicationCode;
    }
}