package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(final Connection connection = Util.getConnection();
            final PreparedStatement createUserTable = connection.prepareStatement(
                    """
                    CREATE TABLE IF NOT EXISTS user
                    (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    lastname VARCHAR(255) NOT NULL,
                    age TINYINT NOT NULL,
                    UNIQUE (name, lastname));
                    """
            ))
        {
            createUserTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try(final Connection connection = Util.getConnection();
            final PreparedStatement dropUserTable = connection.prepareStatement("DROP TABLE IF EXISTS user;"))
        {
            dropUserTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(final Connection connection = Util.getConnection();
            final PreparedStatement saveUser = connection.prepareStatement(
                    """
                        INSERT INTO user(name, lastname, age)
                        values (?, ?, ?)
                        """))
        {
            saveUser.setString(1, name);
            saveUser.setString(2, lastName);
            saveUser.setByte(3, age);
            if (saveUser.executeUpdate() > 0) {
                System.out.printf("User с именем – %s добавлен в базу данных\n", name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(final Connection connection = Util.getConnection();
            final PreparedStatement removeUserById = connection.prepareStatement("DELETE FROM user WHERE id = ?"))
        {
            removeUserById.setLong(1, id);
            removeUserById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (final Connection connection = Util.getConnection()) {
            PreparedStatement getAllUsers = connection.prepareStatement("SELECT * FROM user");
            final ResultSet resultSet = getAllUsers.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(final Connection connection = Util.getConnection();
            final PreparedStatement cleanUserTable = connection.prepareStatement("TRUNCATE TABLE user"))
        {
            cleanUserTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}