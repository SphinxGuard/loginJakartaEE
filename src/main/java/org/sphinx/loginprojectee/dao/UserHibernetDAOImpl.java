package org.sphinx.loginprojectee.dao;

import org.sphinx.loginprojectee.model.User;
import org.sphinx.loginprojectee.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }
}
