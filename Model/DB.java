package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static DB db;

    private DB() {

    }

    public static DB getInstance() {
        if (db == null) {
            db = new DB();
            return db;
        } else {
            return db;
        }
    }

    public Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
        return c;
    }
}
