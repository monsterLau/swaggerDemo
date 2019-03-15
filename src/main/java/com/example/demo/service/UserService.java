package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> allUser() {
        List<User> userList = this.userMapper.allUser();
        return userList;
    }

    public User getUserById(Integer id){
        User user=userMapper.getUserById(id);
        return user;
    }

    public User userLogin(String username, String password){
        User user=userMapper.userLogin(username, password);
        return user;
    }

    public void deleteUserByUsername(String username) {
        userMapper.deleteUserByUsername(username);
    }

    public User findUserByUsername(String usernmae) {
        User user = userMapper.findUserByUsername(usernmae);
        return user;
    }

    public int sameUsername(String username) {
        int sameUsername=userMapper.sameUsername(username);
        return sameUsername;
    }

    public int samePassword(String id,String password) {
        int samePassword=userMapper.samePassword(id,password);
        return samePassword;
    }

    public void insertUser(String username, String password) {
        userMapper.insertUser(username, password);
    }

    public void updateUserById(Integer id, String password) {
        userMapper.updateUserById(id, password);
    }
}
