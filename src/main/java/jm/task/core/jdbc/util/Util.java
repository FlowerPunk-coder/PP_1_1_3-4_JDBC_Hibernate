package jm.task.core.jdbc.util;



import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    private final static String DB = "jdbc:mysql://localhost:3312/pp";
    private final static String LOGIN = "user";
    private final static String PASS = "password";

    private final static SessionFactory sessionFactory;

    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3312/pp");
            prop.setProperty("dialect", "org.hibernate.dialect.MySql8Dialect");
            prop.setProperty("hibernate.connection.username", "user");
            prop.setProperty("hibernate.connection.password", "password");
            prop.setProperty("hibernate.show_sql", "true");


            sessionFactory = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw  new ExceptionInInitializerError(ex);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB, LOGIN, PASS);
    }

    public static Session getSession() throws HibernateException {
        return  sessionFactory.openSession();
    }


}
