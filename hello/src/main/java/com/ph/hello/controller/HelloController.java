package com.ph.hello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 14:59
 */
@RestController
public class HelloController {
  @Value("${user.name}")
  private  String name;
  @GetMapping("/hello")
  public String sayHello(){
    return "hello:"+name;
  }

}
