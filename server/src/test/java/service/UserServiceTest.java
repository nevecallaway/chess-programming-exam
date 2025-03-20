package service;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import model.RegisterRequest;
import model.RegisterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserDAO userDAO;
    private AuthDAO authDAO;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
        authDAO = new AuthDAO();
        userService = new UserService(userDAO, authDAO);
    }

    @Test
    public void testRegisterSuccess() throws DataAccessException {
        RegisterRequest request = new RegisterRequest("testuser", "password", "email@example.com");
        RegisterResult result = userService.register(request);

        assertNotNull(result);
        assertEquals("testuser", result.username());
        assertNotNull(result.authToken());
    }

    @Test
    public void testRegisterUserAlreadyExists() throws DataAccessException {
        RegisterRequest request = new RegisterRequest("testuser", "password", "email@example.com");
        userService.register(request);
        DataAccessException thrown = assertThrows(DataAccessException.class, () -> {
            userService.register(request);
        });
        assertEquals("User already exists", thrown.getMessage());
    }
}