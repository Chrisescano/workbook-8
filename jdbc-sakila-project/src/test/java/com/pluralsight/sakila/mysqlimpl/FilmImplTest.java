package com.pluralsight.sakila.mysqlimpl;

import com.pluralsight.sakila.data.SakilaDatabase;
import com.pluralsight.sakila.model.Actor;
import com.pluralsight.sakila.model.Film;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilmImplTest {
    @Test
    void getFilmsByActor() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmImpl filmimpl = new FilmImpl(sakilaDatabase.getDataSource());

        Actor actor = new Actor(1, null, null, null);
        ArrayList<Film> films = filmimpl.getFilmsByActor(actor);

        if (!films.isEmpty()) {
            for (Film film : films) {
                assertNotEquals(0, film.getFilmID());
                assertNotEquals(null, film.getTitle());
                assertNotEquals(0, film.getLanguageID());
            }
        }
    }

    @Test
    void createEntry() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmImpl filmimpl = new FilmImpl(sakilaDatabase.getDataSource());

        Film film = new Film(
                0,
                "Bee Movie",
                "Yah Like Jazz Sonny?",
                1
        );
        film = filmimpl.createEntry(film);

        assertNotEquals(0, film.getFilmID());
        assertEquals("Bee Movie", film.getTitle());
        assertEquals("Yah Like Jazz Sonny?", film.getDescription());
        assertEquals(1, film.getLanguageID());
    }

    @Test
    void readEntry() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmImpl filmimpl = new FilmImpl(sakilaDatabase.getDataSource());

        Film film = filmimpl.readEntry(1);

        assertNotEquals(0, film.getFilmID());
        assertNotEquals(null, film.getTitle());
        assertNotEquals(null, film.getDescription());
        assertNotEquals(0, film.getLanguageID());

        System.out.println(film);
    }

    @Test
    void updateEntry() {
    }

    @Test
    void deleteEntry() {
    }
}