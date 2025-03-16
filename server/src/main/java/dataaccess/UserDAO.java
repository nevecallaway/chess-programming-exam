package dataaccess;

import model.*;

public class UserDAO {
    public void UserData getUser(String username) throws DataAccessException {
        try {
            // Implementation to find user by username
        } catch (Exception e) {
            throw new DataAccessException("Error accessing user data", e);
        }
    }

    public void createUser(UserData userData) throws DataAccessException {
        try {
            // Implementation to create user
        } catch (Exception e) {
            throw new DataAccessException("Error creating user data", e);
        }
    }

    public void createAuth(AuthData authData) throws DataAccessException {
        try {
            // Implementation to create auth data
        } catch (Exception e) {
            throw new DataAccessException("Error creating auth data", e);
        }
    }
}
