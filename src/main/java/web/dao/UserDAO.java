package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> displayAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void updateUser(User user);

    void deleteUser(User user);
}
