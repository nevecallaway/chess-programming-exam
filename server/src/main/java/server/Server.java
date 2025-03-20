package server;

import spark.Spark;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;
import service.UserService;

public class Server {
    public int run(int desiredPort) {
        Spark.port(desiredPort);
        Spark.staticFiles.location("web");

        UserDAO userDAO = new UserDAO();
        AuthDAO authDAO = new AuthDAO();
        UserService userService = new UserService(userDAO, authDAO);

        Spark.post("/user", new RegisterHandler(userService));

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
    }
}