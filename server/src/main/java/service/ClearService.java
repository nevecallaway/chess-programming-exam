package service;

import dataaccess.ClearDAO;
import dataaccess.DataAccessException;

public class ClearService {
    private final ClearDAO clearDAO;

    public ClearService(ClearDAO clearDAO) {
        this.clearDAO = clearDAO;
    }

    public void clear() throws DataAccessException {
        clearDAO.clear();
    }
}