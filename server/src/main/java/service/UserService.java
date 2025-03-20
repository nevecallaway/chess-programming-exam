package service;

import dataaccess.*;
import model.*;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public RegisterResult register(RegisterRequest registerRequest) throws DataAccessException {
        UserData existingUser = userDAO.getUser(registerRequest.username());
        if (existingUser != null) {
            throw new DataAccessException("User already exists");
        }

        UserData newUser = new UserData(registerRequest.username(), registerRequest.password(),
                                        registerRequest.email());
        userDAO.createUser(newUser);

        String authToken = UUID.randomUUID().toString();
        AuthData authData = new AuthData(authToken, registerRequest.username());
        authDAO.createAuth(authData);

        return new RegisterResult(registerRequest.username(), authToken);
    }
}
