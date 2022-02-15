package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "        `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "        `name` VARCHAR(45) NOT NULL,\n" +
                    "        `lastName` VARCHAR(45) NOT NULL,\n" +
                    "        `age` INT NULL,\n" +
                    "        PRIMARY KEY (`id`));");
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("DROP TABLE IF EXISTS users ");
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = Util.getConnection().prepareStatement("INSERT INTO users(name, lastName, age)VALUES (?,?,?)");
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("delete from users where id=" + id);
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUsers() {
        List<User> res = new ArrayList<>();
        ResultSet resultSet;
        try {
            Statement statement = Util.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                res.add(user);
            }
        } catch (SQLException e) {
        }
        return res;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute("delete from users");
        } catch (SQLException e) {
        }
    }
}
