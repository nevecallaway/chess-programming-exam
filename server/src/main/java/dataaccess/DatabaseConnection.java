package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/chess_db";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "nevecallaway";

    /*private static final String DATABASE_USER = System.getenv("DB_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DB_PASSWORD");*/

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load MySQL JDBC driver", e);
        }
    }

    public Connection getConnection() throws DataAccessException {
        Connection connection = null;
        try {
            logger.info("Attempting to connect to database: " + DATABASE_URL);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to connect to database", e);
            throw new DataAccessException("Unable to connect to database", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Failed to close database connection", e);
            }
        }
    }
}