package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.dao.UserDAO;
import spr.entity.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> index() {
        return userDao.index();
    }

    @Override
    @Transactional(readOnly = true)
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(long id, User updateUser) {
        User user = new User();
        user.setId(id);
        user.setAge(updateUser.getAge());
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }
}
