package org.pluralsight.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Actor {
    private final int actorID;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime timestamp;

    public Actor(int actorID, String firstName, String lastName, LocalDateTime timestamp) {
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timestamp = timestamp;
    }

    /*-----Getter-----*/
    public int getActorID() {
        return actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("ID: %5d, Name: %-20s %-20s, Last Update: %s",
                actorID, firstName, lastName, timestamp);
    }
}
