package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Максим", "Кетиков", (byte) 41);
        userService.saveUser("Иван", "Минаев", (byte) 24);
        userService.saveUser("Федор", "Вознесенский", (byte) 35);
        userService.saveUser("Лев", "Токарев", (byte) 16);
        List<User> users = userService.getAllUsers();
        for(User user : users) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
