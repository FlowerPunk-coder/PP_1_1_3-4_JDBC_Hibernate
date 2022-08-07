package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {

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
