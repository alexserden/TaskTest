package ua.varus.testtask.service;

import ua.varus.testtask.model.User;


public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
