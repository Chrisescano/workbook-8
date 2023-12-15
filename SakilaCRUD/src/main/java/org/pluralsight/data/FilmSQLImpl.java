package org.pluralsight.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.pluralsight.model.Film;

import java.sql.*;

public class FilmSQLImpl implements SakilaDAO<Film, Integer> {
    private final Connection connection;

    public FilmSQLImpl(BasicDataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Film createEntry(Film object) {
        String sql = """
                INSERT INTO film (title, description, language_id)
                values (?, ?, ?);
                """;

        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getDescription());
            statement.setInt(3, 1); //1 for english
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            result.next();
            return new Film(
                    result.getInt(1),
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
                WHERE film_id = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Film(
                        result.getInt("film_id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getInt("language_id")
                );
            } else {
                System.out.println("Sorry no results found");
                return new Film(); //null film object
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateEntry(Film object) {
        String sql = """
                UPDATE film
                SET title = ?, description = ?, language_id = ?
                WHERE film_id = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getLanguageID());
            statement.setInt(4, object.getFilmID());
            return statement.executeUpdate(); //returns num of rows affected or 0 if nothing affected
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteEntry(Film object) {
        String sql = """
                DELETE FROM film
                WHERE film_id = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, object.getFilmID());
            return statement.executeUpdate(); //return num of rows affected or 0 if nothing affected
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
