package com.ph.user.controller;

import com.ph.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:51
 */
@RestController()
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @GetMapping("/")
  public String hello(){
    return  userService.sayHello();
  }
  @GetMapping("/luck")
  public String luck(){
    return "恭喜你，中将啦";
  }
  @GetMapping("/info")
  public String userInfo(){
    return "用户信息";
  }
  public String waiting(){
    return "排队中";
  }
}
