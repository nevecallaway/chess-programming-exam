import static spark.Spark.*;

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
    public void run(int port) throws DataAccessException {
        port(port);
        staticFiles.location("web");

        // Initialize database connection
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        // Initialize DAOs with the connection
        UserDAO userDAO = new UserDAO(connection);
        AuthDAO authDAO = new AuthDAO(connection);
        ClearDAO clearDAO = new ClearDAO(connection);

        // Initialize services with the DAOs
        UserService userService = new UserService(userDAO, authDAO);
        ClearService clearService = new ClearService(clearDAO);

        // Define the endpoints
        post("/user", new RegisterHandler(userService));
        post("/clear", new ClearHandler(clearService));

        System.out.println("Server started on http://localhost:" + port);
    }
}