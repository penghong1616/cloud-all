package cn.zhangxd.gateway.controller;

import cn.zhangxd.gateway.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
  @HystrixCommand(fallbackMethod = "waiting")
  public String luck(){
    return "恭喜你，中将啦";
  }
  public String waiting(){
    return "排队中";
  }
}
