package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
//import javax.security.auth.login.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/firstdb";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connection;
    public static SessionFactory sessionFactory;

    private Util() {
    }

    //JDBC Connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            } catch (SQLException e) {
            }
        }
        return connection;
    }

    //SessionFactory
    public static SessionFactory getHibernateSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties prop = new Properties();

                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                prop.put(Environment.URL, URL);
                prop.put(Environment.USER, "root");
                prop.put(Environment.PASS, "root");

                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                //prop.put(Environment.SHOW_SQL, "true");
                //prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //prop.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(prop);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
            }
        }
        return sessionFactory;
    }

}
