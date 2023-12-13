package org.pluralsight.model;

import java.time.LocalDate;

public class Film {
    private int filmID;
    private String title;
    private String description;
    private LocalDate releaseYear;
    private int length;

    public Film(int filmID, String title, String description, LocalDate releaseYear, int length) {
        this.filmID = filmID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.length = length;
    }

    /*-----Getters-----*/

    public int getFilmID() {
        return filmID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear.getYear();
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return String.format("ID: %5d Title: %-20.20s Description: %-20.20s Release: %d Length: %d",
                filmID, title, description, releaseYear.getYear(), length);
    }
}
