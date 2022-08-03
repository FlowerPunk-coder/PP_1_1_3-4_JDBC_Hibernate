package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Максим", "Кетиков", (byte) 41);
        userDaoJDBC.saveUser("Иван", "Минаев", (byte) 24);
        userDaoJDBC.saveUser("Федор", "Вознесенский", (byte) 35);
        userDaoJDBC.saveUser("Лев", "Токарев", (byte) 16);
        List<User> users = userDaoJDBC.getAllUsers();
        for(User user : users) {
            System.out.println(user.toString());
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
