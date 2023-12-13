package org.pluralsight.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.pluralsight.model.Actor;
import org.pluralsight.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataManager {
    Connection connection;

    public DataManager(BasicDataSource basicDataSource) {
        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Actor> getActorByLastName(String firstName, String lastName) {
        ArrayList<Actor> actors = new ArrayList<>();
        String sql = """
                SELECT * FROM actor as a
                WHERE a.first_name = ? OR a.last_name = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet results = statement.executeQuery();

            //handle case where no results
            if (results.next()) {
                do {
                    LocalDateTime timestamp = null;
                    if (results.getTimestamp("last_update") != null)
                        timestamp = results.getTimestamp("last_update").toLocalDateTime();

                    actors.add(new Actor(
                            results.getInt("actor_id"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            timestamp
                    ));
                } while (results.next());
            } else {
                System.out.println("No Matches Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return actors;
    }

    public ArrayList<Film> getFilmsByActor(Actor actor) {
        ArrayList<Film> films = new ArrayList<>();
        String sql = """
                SELECT f.film_id, f.title, f.release_year, f.`description`, f.release_year, f.length
                FROM film_actor AS fa
                JOIN film AS f
                ON fa.film_id = f.film_id
                JOIN actor AS a
                ON a.actor_id = fa.actor_id
                WHERE a.actor_id = ?;
                """;

        //description, release year, and length can be null

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, actor.getActorID());
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                do {
                    String description = null;
                    if (results.getString("f.description") != null)
                        description = results.getString("f.description");

                    LocalDate releaseYear = null;
                    if (results.getDate("f.release_year") != null)
                        releaseYear = results.getDate("f.release_year").toLocalDate();

                    // if length is null 0 is returned

                    films.add(new Film(
                            results.getInt("f.film_id"),
                            results.getString("f.title"),
                            description,
                            releaseYear,
                            results.getInt("f.length")
                    ));

                } while (results.next());
            } else {
                System.out.println("No Films Found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return films;
    }
}
