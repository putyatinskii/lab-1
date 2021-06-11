package database_connection;

import java.sql.*;


public class DataBase {

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/TaskManager";
            String login = "postgres";
            String password = "341678";
            return DriverManager.getConnection(url, login, password);
    }
}
