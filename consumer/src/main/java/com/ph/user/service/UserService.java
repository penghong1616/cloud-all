package com.ph.user.service;

import com.ph.user.fallback.UserServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:45
 */
@FeignClient(value = "provider-service",path = "/user",fallback = UserServiceFallBack.class)
public interface UserService {
  @GetMapping("/sayHello")
  String sayHello();

}
