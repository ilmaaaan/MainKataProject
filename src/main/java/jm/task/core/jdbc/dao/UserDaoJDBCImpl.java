package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void createUsersTable() throws SQLException {
        Statement statement = null;

        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.getUpdateCount();
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы users");;
        }

    }

    @Override
    public void dropUsersTable() throws SQLException {
        Statement statement = null;

        String sql = "drop table if exists users;";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы users");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "insert into users (name, lastName, age) values (?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении нового user в таблицу users");
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id = ?;";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении user из таблицы users");
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        String sql = "select id, name, lastName, age from users;";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех userов из таблицы users");
        }
        userList.forEach(x -> System.out.println(x.toString()));
        return userList;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        Statement statement = null;

        String sql = "delete from users;";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Ошибка при очищении таблицы users");
        }
    }
}
