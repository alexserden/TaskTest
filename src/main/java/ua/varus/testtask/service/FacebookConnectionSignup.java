package ua.varus.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import ua.varus.testtask.dao.UserDao;
import ua.varus.testtask.model.User;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    private UserDao userDao;
    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(user.getPassword());
        userDao.save(user);
        return user.getUsername();    }
}
