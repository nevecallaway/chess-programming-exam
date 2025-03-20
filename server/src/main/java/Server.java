import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import server.RegisterHandler;
import service.UserService;

import static spark.Spark.*;

public class Server {
    public void run(int port) {
        port(port);
        staticFiles.location("web");

        //get("/", (req, res) -> "240 Chess Server is running!");

        UserService userService = new UserService(new UserDAO(), new AuthDAO());

        // Define the /user endpoint
        post("/user", new RegisterHandler(userService));

        System.out.println("Server started on http://localhost:" + port);
    }
}
