package org.sphinx.loginprojectee.dao;

import org.sphinx.loginprojectee.exceptions.UserNotFoundException;
import org.sphinx.loginprojectee.model.User;

public interface UserDAO {
    public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException;

}
