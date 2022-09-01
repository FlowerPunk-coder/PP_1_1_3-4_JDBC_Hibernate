package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Олег", "Венидиктов", (byte) 34);
        userService.saveUser("Томас", "Брукс", (byte) 14);
        userService.saveUser("Изя", "Брендель", (byte) 35);
        userService.saveUser("Оксана", "Петровна", (byte) 92);

        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
