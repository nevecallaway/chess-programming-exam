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
            // Parse JSON request body
            RegisterRequest registerRequest = gson.fromJson(req.body(), RegisterRequest.class);
            RegisterResult result = userService.register(registerRequest);

            res.status(200);
            res.type("application/json");
            return gson.toJson(result);
        } catch (DataAccessException e) {
            res.status(409);
            res.type("application/json");
            return gson.toJson(new ErrorResponse("User already exists"));
        } catch (Exception e) {
            res.status(500);
            res.type("application/json");
            return gson.toJson(new ErrorResponse("Internal server error"));
        }
    }

    // Error response class
    private static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}