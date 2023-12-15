package org.pluralsight.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.pluralsight.model.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorSQLImpl implements SakilaDAO<Actor, Integer> {
    private final Connection connection;

    public ActorSQLImpl(BasicDataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Actor createEntry(Actor object) {
        String sql = """
                INSERT INTO actor (actor_id, first_name, last_name, last_update)
                VALUES (?, ?, ?, ?);
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, object.getActorID());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(object.getTimestamp()));
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            result.next();
            return new Actor(
                    result.getInt(1),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getTimestamp("last_update").toLocalDateTime()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Actor readEntry(Integer key) {
        String sql = """
                SELECT * FROM actor
                WHERE actor_id = ?;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Actor(
                        result.getInt("actor_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getTimestamp("last_update").toLocalDateTime()
                );
            } else {
                return new Actor(); //null actor object
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateEntry(Actor object) {
        return 0;
    }

    @Override
    public int deleteEntry(Actor object) {
        return 0;
    }
}
