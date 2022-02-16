package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import GenerateRandomUser.GenerateUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        UserServiceImpl userDaoJDBC = new UserServiceImpl();

        userDaoJDBC.createUsersTable();
        for (int i = 0; i < 4; i++) {
            userDaoJDBC.saveUser(GenerateUser.getName(), GenerateUser.getLastName(), GenerateUser.getAge());
            System.out.println("User с именем – name добавлен в базу данных");
        }



        for (User user:userDaoJDBC.getAllUsers()) {
            System.out.println(user);
        }




        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
