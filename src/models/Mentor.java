package models;

public class Mentor {

    private int id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String city;
    private int favouriteNumber;

    public Mentor(int id, String firstName, String lastName, String nickName, String phoneNumber,
                  String email, String city, int favouriteNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.favouriteNumber = favouriteNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    @Override
    public String toString() {
        return String.format("ID: %d %s %s %s %s %s %s %d", id, firstName, lastName, nickName,
                phoneNumber, email, city, favouriteNumber);
    }
}