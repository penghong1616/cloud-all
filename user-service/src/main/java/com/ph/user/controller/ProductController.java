package com.ph.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/27/ 14:38
 */
@RestController
@RequestMapping("/product"
)
public class ProductController {
  @GetMapping("/info")
  public String info(){
    return "产品信息";
  }

}
