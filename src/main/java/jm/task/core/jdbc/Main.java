package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("al", "an", (byte) 30);
        System.out.println("User с именем – name добавлен в базу данных");
        userDaoJDBC.saveUser("an", "nan", (byte) 10);
        System.out.println("User с именем – name добавлен в базу данных");
        userDaoJDBC.saveUser("hl", "ahn", (byte) 12);
        System.out.println("User с именем – name добавлен в базу данных");
        userDaoJDBC.saveUser("hgl", "ahn", (byte) 12);
        System.out.println("User с именем – name добавлен в базу данных");
        for (User i: userDaoJDBC.getAllUsers()) {
            System.out.println(i);
        }
        userDaoJDBC.dropUsersTable();
    }
}
