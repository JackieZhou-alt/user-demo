package com.baizhi.service.impl;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @Cacheable("users")
    public List<User> selectUser(Integer pageNum,Integer pageSize, String query) {
        System.out.println("UserServiceImpl.selectUser");
        List<User> users = userMapper.selectUser(pageNum, pageSize, query);
        return users;
    }

    @Override
    @Cacheable("users")
    public User selectOneUser(Integer id) {
        User user = userMapper.selectOneUser(id);
        return user;
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
