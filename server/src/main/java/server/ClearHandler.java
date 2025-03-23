package server;

import service.ClearService;
import spark.Request;
import spark.Response;
import spark.Route;
import com.google.gson.Gson;

public class ClearHandler implements Route {
    private final ClearService clearService;
    private final Gson gson = new Gson();

    public ClearHandler(ClearService clearService) {
        this.clearService = clearService;
    }

    @Override
    public Object handle(Request req, Response res) throws Exception {
        try {
            clearService.clear();
            res.status(200);
            res.type("application/json");
            return gson.toJson(new ClearResult("Clear succeeded"));
        } catch (Exception e) {
            res.status(500);
            res.type("application/json");
            return gson.toJson(new ErrorResponse("Internal server error"));
        }
    }

    // Clear result class
    private static class ClearResult {
        private final String message;

        public ClearResult(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
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