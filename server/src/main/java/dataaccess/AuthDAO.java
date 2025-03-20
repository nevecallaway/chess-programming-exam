package dataaccess;

import model.AuthData;
import java.util.HashMap;
import java.util.Map;

public class AuthDAO {
    private Map<String, AuthData> authTokens = new HashMap<>();

    public void createAuth(AuthData authData) throws DataAccessException {
        if (authTokens.containsKey(authData.getToken())) {
            throw new DataAccessException("Auth token already exists");
        }
        authTokens.put(authData.getToken(), authData);
    }

}