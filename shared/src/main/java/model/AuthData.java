package model;

public class AuthData {
    private String token;
    private String username;

    public AuthData(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}