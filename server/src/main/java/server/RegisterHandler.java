package server;

import dataaccess.DataAccessException;
import service.UserService;
import model.RegisterRequest;
import model.RegisterResult;
import spark.Request;
import spark.Response;
import spark.Route;
import com.google.gson.Gson;

public class RegisterHandler implements Route {
    private final UserService userService;
    private final Gson gson = new Gson();

    public RegisterHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object handle(Request req, Response res) throws Exception {
        try {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String email = req.queryParams("email");

            RegisterRequest registerRequest = new RegisterRequest(username, password, email);
            RegisterResult result = userService.register(registerRequest);

            res.status(200);
            res.type("application/json");
            return gson.toJson(result);
        } catch (DataAccessException e) {
            res.status(409);
            res.type("application/json");
            return gson.toJson(new Exception("User already exists"));
        }
    }
}