package com.example.demo.controller;

import Utils.MD5;
import com.example.demo.entity.ResponseData;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "userController", tags = "用户管理接口列表")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public ResponseData userLogin(@ApiParam(value = "username", required = true) @RequestParam("username") String username,
                                  @ApiParam(value = "password", required = true) @RequestParam("password") String password) {
        ResponseData responseData;
        String MD5password = MD5.MD5Encode(password, "utf8", false);
        User user = userService.userLogin(username, MD5password);
        if (user == null) {
            responseData = new ResponseData("passwordOrUsernameIsWrong", "301");
            return responseData;
        }
        responseData = new ResponseData("Success", "200");
        return responseData;
    }

    @ApiOperation(value = "查询所有用户", httpMethod = "GET")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public ResponseData allUser() {
        ResponseData responseData;
        List<User> userList = userService.allUser();
        responseData = new ResponseData("Success", "200");
        responseData.setData(userList);

        return responseData;
    }

    @ApiOperation(value = "删除用户", httpMethod = "POST")
    @Transactional
    @RequestMapping(value = "/delUser")
    public ResponseData deleteUserByUsername(@ApiParam(value = "username", required = true) @RequestParam(value = "username", required = true) String username) {
        ResponseData responseData;
        User user = userService.findUserByUsername(username);
        if (user == null) {
            responseData = new ResponseData("user not exit!", "301");
            return responseData;
        }
        userService.deleteUserByUsername(username);
        responseData = new ResponseData("success!", "200");

        return responseData;
    }

    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @RequestMapping(value = "/insertUser")
    @Transactional
    public ResponseData insertUser(@ApiParam(value = "username", required = true) @RequestParam("username") String username,
                                   @ApiParam(value = "password", required = true) @RequestParam("password") String password) {
        ResponseData responseData;
        int sameUsername = userService.sameUsername(username);

        if (sameUsername > 0) {
            responseData = new ResponseData("sameUsername", "301");
            return responseData;
        }
        String newPassword = MD5.MD5Encode(password, "utf8", false);
        userService.insertUser(username, newPassword);
        responseData = new ResponseData("200", "Success");
        return responseData;
    }

    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @Transactional
    @PostMapping("/updateUserPasswordById")
    public ResponseData updateUserById(@ApiParam(value = "userId", required = true) @RequestParam("id") Integer id,
                                       @ApiParam(value = "newPassword", required = true) @RequestParam("newPassword") String newPassword) {
        ResponseData responseData;
        User user = userService.getUserById(id);
        String oldPassword = user.getPassword();
        String MD5newPassword = MD5.MD5Encode(newPassword, "utf8", false);

        if (oldPassword.equals(MD5newPassword)) {
            responseData = new ResponseData("新旧密码重复", "301");
            return responseData;
        }
        userService.updateUserById(id, MD5newPassword);
        responseData = new ResponseData("Success", "200");
        return responseData;
    }
}
