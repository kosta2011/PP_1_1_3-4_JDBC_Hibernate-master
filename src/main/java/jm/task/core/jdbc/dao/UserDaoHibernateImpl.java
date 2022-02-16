package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session s = Util.getHibernateSession().openSession();
        s.beginTransaction();


        Query query = s.createSQLQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                "        `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "        `name` VARCHAR(45) NOT NULL,\n" +
                "        `lastName` VARCHAR(45) NOT NULL,\n" +
                "        `age` INT NULL,\n" +
                "        PRIMARY KEY (`id`));").addEntity(User.class);

        query.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void dropUsersTable() {
        Session s = Util.getHibernateSession().openSession();
        s.beginTransaction();
        Query query = s.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
        query.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session s = Util.getHibernateSession().openSession();
        s.beginTransaction();

        s.save(new User(name, lastName, age));

        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void removeUserById(long id) {
        Session s = Util.getHibernateSession().openSession();
        s.beginTransaction();

        s.remove(s.get(User.class, id));

        s.getTransaction().commit();
        s.close();
    }

    @Override
    public List<User> getAllUsers() {
        return Util.getHibernateSession()
                .openSession().createQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {
        Session s = Util.getHibernateSession().openSession();
        s.beginTransaction();
        Query query = s.createSQLQuery("delete from users").addEntity(User.class);
        query.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }
}
