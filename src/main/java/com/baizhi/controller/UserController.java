package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    //@RequestMapping("show")
    @GetMapping("/users")
    public Map show(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "") String query){
        List<User> users = userService.selectUser(pageNum, pageSize, query);
        PageInfo pageInfo = new PageInfo(users,10);
        int[] pages = pageInfo.getNavigatepageNums();
        long total = pageInfo.getTotal();
        Map map = new HashMap();
        map.put("users",users);
        map.put("pages",pages);
        map.put("total",total);

        return map;
    }
    //@RequestMapping("add")
    @PostMapping("/users")
    public Map add(@RequestBody User u){
        Map map = new HashMap();
        try {
            u.setCreateTime(new Date());
            userService.addUser(u);
            map.put("status","success");
        } catch (Exception e) {
            map.put("status","failed");
            throw new RuntimeException(e);
        }
        return map;
    }
    //@RequestMapping("remove")
    @DeleteMapping("/users/{id}")
    public Map remove(@PathVariable("id") Integer id){
        Map map = new HashMap();
        try {
            userService.deleteUser(id);
            map.put("status","success");
        } catch (Exception e) {
            map.put("status","failed");
            throw new RuntimeException(e);
        }
        return map;
    }
    //@RequestMapping("showOne")
    @GetMapping("/users/{id}")
    public User showOne(@PathVariable("id") Integer id){
        User user = userService.selectOneUser(id);
        return user;
    }
    //@RequestMapping("update")
    @PutMapping("/users")
    public Map update(@RequestBody User u){
        Map map = new HashMap();
        try {
            u.setUpdateTime(new Date());
            userService.updateUser(u);
            map.put("status","success");
        } catch (Exception e) {
            map.put("status","failed");
            throw new RuntimeException(e);
        }
        return map;
    }



}
