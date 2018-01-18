package models;

public class Applicant {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String applicationCode;

    public Applicant(String firstName, String lastName, String phoneNumber, String email, String applicationCode) {
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

    public String getApplicationCode() {
        return applicationCode;
    }
}