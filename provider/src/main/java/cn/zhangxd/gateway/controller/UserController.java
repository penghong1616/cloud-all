package cn.zhangxd.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/19/ 9:25
 */
@RestController()
@RequestMapping("/user")
public class UserController {
  @GetMapping("/sayHello")
  public String sayHello(){
    return "我是provider";
  }


}
