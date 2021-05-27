package com.ph.svca.controller;

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

  @RequestMapping(value = "/registry", method = RequestMethod.POST)
  public String createUser(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    return "注册车工";
  }
}
