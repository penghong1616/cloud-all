package com.ph.svca.controller;

import com.ph.pojo.User;
import com.ph.svca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 13:20
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/registry", method = RequestMethod.POST)
  public User createUser(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    return userService.create(username,password);
  }
}
