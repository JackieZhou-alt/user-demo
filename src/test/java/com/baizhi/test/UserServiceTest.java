package com.baizhi.test;

import com.baizhi.UserDemoApplication;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserDemoApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSelectUser(){
        List<User> users = userService.selectUser(1, 2, "a");
        users.forEach(user-> System.out.println(user));
        List<User> users2 = userService.selectUser(1, 2, "a");
        users.forEach(user2-> System.out.println(user2));
    }
    @Test
    public void testAddUser(){
        userService.addUser(new User(null,"小郭22","1234567568@qq.com","123456","123","管理员",0,new Date(),null));
    }
}
