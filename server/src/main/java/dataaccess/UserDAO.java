package dataaccess;

import model.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private Map<String, UserData> users = new HashMap<>();
    private Map<String, AuthData> authDataMap = new HashMap<>();
    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public UserData getUser(String username) throws DataAccessException {
        return users.get(username);
    }

    public void createUser(UserData userData) throws DataAccessException {
        if (users.containsKey(userData.username())) {
            throw new DataAccessException("User already exists");
        }
        users.put(userData.username(), userData);
    }

    public void createAuth(AuthData authData) throws DataAccessException {
        if (authDataMap.containsKey(authData.getToken())) {
            throw new DataAccessException("Auth token already exists");
        }
        authDataMap.put(authData.getToken(), authData);
    }
}
