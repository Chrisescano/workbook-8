package com.pluralsight.sakila.model;

public class Film {
    private final int filmID;
    private final String title;
    private final String description;
    private final int languageID;

    public Film() { //a null film object
        this(0, null, null, 0);
    }

    public Film(int filmID, String title, String description, int languageID) {
        this.filmID = filmID;
        this.title = title;
        this.description = description;
        this.languageID = languageID;
    }

    /*-----Getter-----*/
    public int getFilmID() {
        return filmID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLanguageID() {
        return languageID;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", languageID=" + languageID +
                '}';
    }
}
