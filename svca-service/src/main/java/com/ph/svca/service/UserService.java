package com.ph.svca.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:45
 */
@FeignClient(value = "user-service",path = "/user")
public interface UserService {
  @GetMapping("/info")
  String info();

}
