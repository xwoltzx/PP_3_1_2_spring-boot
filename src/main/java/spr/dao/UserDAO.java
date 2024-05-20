package spr.dao;


import spr.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> index();

    User show(long id);

    void save(User user);

    void update(long id, User updateUser);

    void delete(long id);

}
