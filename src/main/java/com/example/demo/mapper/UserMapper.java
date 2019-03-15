package com.example.demo.mapper;

import com.example.demo.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> allUser();

    public User updateUser(User user);

    public User userLogin(@Param("username") String username,@Param("password") String password);

    public User getUserById(@Param("id")Integer id);

    public void deleteUserByUsername(@Param("username") String username);

    public User findUserByUsername(@Param("username")String username);

    public int sameUsername(@Param("username")String username);

    public int samePassword(@Param("id")String id,@Param("oldPassword") String password);

    public void insertUser(@Param("username") String username,@Param("password") String password);

    public void updateUserById(@Param("id")Integer id,@Param("newPassword") String password);
}
