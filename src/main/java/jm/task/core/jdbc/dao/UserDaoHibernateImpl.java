package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
       runInsideSession( session -> session.createSQLQuery(
               """
               CREATE TABLE IF NOT EXISTS user
               (id BIGINT AUTO_INCREMENT PRIMARY KEY,
               name VARCHAR(255) NOT NULL,
               lastname VARCHAR(255) NOT NULL,
               age TINYINT NOT NULL,
               UNIQUE (name, lastname));
               """).executeUpdate());
    }
    @Override
    public void dropUsersTable() {
       runInsideSession( session -> session.createSQLQuery("DROP TABLE IF EXISTS user;").executeUpdate());
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        runInsideSession( session -> {
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        });
    }
    @Override
    public void removeUserById(long id) {
        runInsideSession( session -> {
            final User user = session.find(User.class, id);
            session.delete(user);
        });
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try(final Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("select u from User u", User.class).getResultList();
            transaction.commit();
        }
        return users;
    }
    @Override
    public void cleanUsersTable() {
        runInsideSession( session -> session.createNativeQuery("TRUNCATE TABLE user").executeUpdate());
    }
    public void runInsideSession(Consumer<Session> consumer) {
        Transaction transaction = null;
        try (final Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
