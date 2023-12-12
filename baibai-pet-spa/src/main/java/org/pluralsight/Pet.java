package org.pluralsight;

import java.time.LocalDate;

public class Pet {
    int petID;
    int ownerID;
    String name;
    String species;
    LocalDate birthday;

    public Pet(int petID, int ownerID, String name, String species, LocalDate birthday) {
        this.petID = petID;
        this.ownerID = ownerID;
        this.name = name;
        this.species = species;
        this.birthday = birthday;
    }

    /*-----Getters-----*/
    public int getPetID() {
        return petID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    /*-----Setters-----*/
    public void setOwnerID(int ownerID) { //useful? maybe
        this.ownerID = ownerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
