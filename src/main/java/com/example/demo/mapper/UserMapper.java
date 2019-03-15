package com.example.demo.mapper;

import com.example.demo.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 列出所有用户信息
     *
     * @return
     */
    public List<User> allUser();

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    public User updateUser(User user);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    public User userLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public User getUserById(@Param("id") Integer id);

    /**
     * 根据username删除用户信息
     *
     * @param username
     */
    public void deleteUserByUsername(@Param("username") String username);

    /**
     * 根据username查询用户信息
     *
     * @param username
     * @return
     */
    public User findUserByUsername(@Param("username") String username);

    /**
     * 判断是否有重复的用户名
     *
     * @param username
     * @return
     */
    public int sameUsername(@Param("username") String username);

    /**
     * 根据id查询用户判断用户新密码是否与旧密码重复
     *
     * @param id
     * @param password
     * @return
     */
    public int samePassword(@Param("id") String id, @Param("oldPassword") String password);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    public void insertUser(@Param("username") String username, @Param("password") String password);

    /**
     * 修改用户信息
     *
     * @param id
     * @param password
     */
    public void updateUserById(@Param("id") Integer id, @Param("newPassword") String password);
}
