package service;

import dao.UserDao;
import model.User;

public class AuthService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    public boolean isUserExists(String username) {
        return userDao.exists(username);
    }
}
