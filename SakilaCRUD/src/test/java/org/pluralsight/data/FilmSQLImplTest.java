package org.pluralsight.data;

import org.junit.jupiter.api.Test;
import org.pluralsight.model.Film;

import static org.junit.jupiter.api.Assertions.*;

class FilmSQLImplTest {
    @Test
    public void createEntryTest() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmSQLImpl filmSQL = new FilmSQLImpl(sakilaDatabase.getDataSource());
        Film film = new Film(0, "Bai Bai Great Adventures", "Join a cat as he saunters through japan", 1);
        film = filmSQL.createEntry(film);
        System.out.println(film);
    }

    @Test
    public void readEntryTest() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmSQLImpl filmSQL = new FilmSQLImpl(sakilaDatabase.getDataSource());
        Film film = filmSQL.readEntry(1);

        assertNotEquals(0, film.getFilmID());
        assertNotEquals(null, film.getTitle());
        assertNotEquals(null, film.getDescription());
        assertNotEquals(0, film.getLanguageID());
    }

    @Test
    public void updateEntryTest() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmSQLImpl filmSQL = new FilmSQLImpl(sakilaDatabase.getDataSource());
        Film film = new Film(1003, "Bai Bai Bodega Cat", "Sauntering in the japanese bodega wilds", 1);
        int numberOfRows = filmSQL.updateEntry(film);

        assertEquals(1, numberOfRows);
    }

    //this test is not great since filmID is auto incrementing and does not cascade
    @Test
    public void deleteEntryTest() {
        SakilaDatabase sakilaDatabase = new SakilaDatabase();
        FilmSQLImpl filmSQL = new FilmSQLImpl(sakilaDatabase.getDataSource());
        Film film = new Film(1003, "Bai Bai Bodega Cat", "Sauntering in the japanese bodega wilds", 1);
        int numberOfRows = filmSQL.deleteEntry(film);

        assertEquals(1, numberOfRows);
    }
}