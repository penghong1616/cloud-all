package cn.zhangxd.gateway.controller;

import cn.zhangxd.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:51
 */
@RestController("/user")
public class UserController {

  @Autowired
  UserService userService;
  @GetMapping("/")
  public String hello(){
    return  userService.sayHello();
  }
}
