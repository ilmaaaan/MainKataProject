package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Util.getConnection();
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Name1", "LastName1", (byte) 20);
        service.saveUser("Name2", "LastName2", (byte) 25);
        service.saveUser("Name3", "LastName3", (byte) 31);
        service.saveUser("Name4", "LastName4", (byte) 38);

        service.removeUserById(1);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
        service.cleanUsersTable();
        Util.closeConnection();
    }
}
