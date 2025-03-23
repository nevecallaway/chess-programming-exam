package server;

import spark.Spark;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;
import dataaccess.ClearDAO;
import dataaccess.DatabaseConnection;
import dataaccess.DataAccessException;
import service.UserService;
import service.ClearService;
import server.RegisterHandler;
import server.ClearHandler;

import java.sql.Connection;

public class Server {
    public int run(int desiredPort) {
        try {
            Spark.port(desiredPort);
            Spark.staticFiles.location("web");

            // Initialize database connection
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();

            // Initialize DAOs with the connection
            UserDAO userDAO = new UserDAO(connection);
            AuthDAO authDAO = new AuthDAO(connection);

            // Initialize services with the DAOs
            UserService userService = new UserService(userDAO, authDAO);
            ClearService clearService = new ClearService(new ClearDAO(connection));

            // Define the endpoints
            Spark.post("/user", new RegisterHandler(userService));
            Spark.post("/clear", new ClearHandler(clearService));

            Spark.awaitInitialization();
            return Spark.port();
        } catch (DataAccessException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
            return -1; // Indicate failure to start the server
        }
    }

    public void stop() {
        Spark.stop();
    }
}