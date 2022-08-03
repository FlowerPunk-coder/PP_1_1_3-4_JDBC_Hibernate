package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Максим", "Кетиков", (byte) 41);
        userService.saveUser("Иван", "Минаев", (byte) 24);
        userService.saveUser("Федор", "Вознесенский", (byte) 35);
        userService.saveUser("Лев", "Токарев", (byte) 16);
        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();


        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Олег", "Венидиктов", (byte) 34);
        userDao.saveUser("Томас", "Брукс", (byte) 14);
        userDao.saveUser("Изя", "Брендель", (byte) 35);
        userDao.saveUser("Оксана", "Петровна", (byte) 92);

        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
