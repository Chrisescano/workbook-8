package com.pluralsight.sakila.mysqlimpl;

import com.pluralsight.sakila.data.DataAccessObject;
import com.pluralsight.sakila.model.Actor;
import com.pluralsight.sakila.model.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

public class FilmImpl implements DataAccessObject<Film, Integer> {
    Connection connection;

    public FilmImpl(BasicDataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Film> getFilmsByActor(Actor actor) {
        ArrayList<Film> films = new ArrayList<>();
        String sql = """
                SELECT * FROM film AS f
                JOIN film_actor AS fa ON f.film_id = fa.film_id
                WHERE fa.actor_id = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, actor.getActorID());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                do {
                    String description = null;
                    if (resultSet.getString("description") != null)
                        description = resultSet.getString("description");

                    films.add(new Film(
                            resultSet.getInt("film_id"), //this is our PK
                            resultSet.getString("title"),
                            description,
                            resultSet.getInt("language_id")
                    ));
                } while (resultSet.next());
            } else {
                System.out.println("No Matches Found");
            }

            return films;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Film createEntry(Film object) {
        String sql = """
                INSERT INTO film (title, description, language_id)
                VALUES (?, ?, ?);
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getLanguageID());
            statement.executeUpdate(); //used for INSERT, UPDATE, DELETE

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); //move the pointer to the first row

            return new Film(
                    resultSet.getInt(1), //this is our PK
                    object.getTitle(),
                    object.getDescription(),
                    object.getLanguageID()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Film readEntry(Integer key) {
        String sql = """
                SELECT * FROM film
                WHERE film_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Film(
                        resultSet.getInt("film_id"), //this is our PK
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("language_id")
                );
            } else {
                System.out.println("No Matches Found");
                return new Film();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateEntry(Film object) {
        return 0;
    }

    @Override
    public int deleteEntry(Film object) {
        return 0;
    }
}
