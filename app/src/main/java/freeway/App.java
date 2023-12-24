package freeway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String jdbcUrl = args[0];
        String migrationDirectory = args[1];
        System.out.println("Called with " + jdbcUrl + " and directory: " + migrationDirectory);
        Executor.execute(jdbcUrl, migrationDirectory);
    }

    public static Connection getConnection(String jdbcUrl) throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }
}
