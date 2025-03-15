import static spark.Spark.*;

public class Server {
    public void run(int port) {
        port(port);

        get("/", (req, res) -> "♕ 240 Chess Server is running!");

        System.out.println("Server started on http://localhost:" + port);
    }
}
