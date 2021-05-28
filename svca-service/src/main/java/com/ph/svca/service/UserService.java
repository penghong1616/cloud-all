package com.ph.svca.service;

import com.ph.svca.fallback.UserServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:45
 */
@FeignClient(value = "user-service",path = "/user",fallback = UserServiceFallBack.class)
public interface UserService {
  @RequestMapping(value = "/info",method = RequestMethod.GET)
  String info();

}
