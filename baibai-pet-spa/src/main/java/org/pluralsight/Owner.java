package org.pluralsight;

public class Owner {
    int ownerID;
    String firstName;
    String lastName;
    String address;
    String phone;

    public Owner(int ownerID, String firstName, String lastName, String address, String phone) {
        this.ownerID = ownerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    /*-----Getters-----*/
    public int getOwnerID() {
        return ownerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
