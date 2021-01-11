package com.baizhi.mapper;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectUser(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize, @Param("query")String query);

    User selectOneUser(Integer id);

    void deleteUser(Integer id);

    void addUser(User user);

    void updateUser(User user);
}
