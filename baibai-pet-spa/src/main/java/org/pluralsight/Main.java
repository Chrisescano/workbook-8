package org.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PetSpaDatabase petSpaDatabase = new PetSpaDatabase();
        PetDataManager petDataManager = new PetDataManager(petSpaDatabase.connection);

        ArrayList<Pet> pets = petDataManager.getAllPets();
        pets.get(0).setBirthday(LocalDate.now().minusYears(14));
        petDataManager.updatePet(pets.get(0));
    }
}
