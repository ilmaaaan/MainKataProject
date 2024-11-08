package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;

    private static String hostName = "localhost";
    private static String dbName = "mydbtest";
    private static String userName = "rootroot";
    private static String password = "root";
    private static String connectionURL = "jdbc:mysql://" + hostName +  ":3306/" + dbName;


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
