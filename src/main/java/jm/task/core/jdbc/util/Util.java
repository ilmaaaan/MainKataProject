package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;

    private static final String hostName = "localhost";
    private static final String dbName = "mydbtest";
    private static final String userName = "rootroot";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://" + hostName +  ":3306/" + dbName;


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void closeConnection() throws SQLException, ClassNotFoundException {
        connection.close();
        if (connection.isClosed()) {
        }
    }
}
