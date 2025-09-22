package org.sphinx.loginprojectee.dao;

import org.sphinx.loginprojectee.model.User;

public interface UserDAO {
    public User findUserById(Long id);
    public User findUserByUsernameAndPassword(String username, String password);
    public void addUser(User user);

}
