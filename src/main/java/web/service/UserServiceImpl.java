package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.UserDao;
import web.model.User;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    @Transactional(readOnly = true)
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> find(User user) {
        return userDao.find(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        return userDao.getList();
    }

}
