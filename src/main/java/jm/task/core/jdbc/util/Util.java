package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/firstdb";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connection;

    private Util() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            } catch (SQLException e) {
            }
        }
        return connection;
    }



}
