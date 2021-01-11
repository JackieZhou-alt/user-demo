package com.baizhi.test;

import com.baizhi.UserDemoApplication;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserDemoApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser(){
        List<User> users = userMapper.selectUser(1, 2, "五");
        //List<User> users = userMapper.selectUser(1, 2,"");
        users.forEach(user-> System.out.println(user));
    }
    @Test
    public void testSelectOneUser(){
        User user = userMapper.selectOneUser(2);
        System.out.println(user);
    }
    @Test
    public void testDeleteUser(){
        userMapper.deleteUser(1);
    }
    @Test
    public void testAddUser(){
        userMapper.addUser(new User(null,"小郭","12345678@qq.com","123456","123","管理员",0,new Date(),null));
    }

    @Test
    public void testUpdateUser(){
        userMapper.updateUser(new User(4,"小郭2","1234567856@qq.com","123456","123456","管理员1",1,null,new Date()));
    }


}
