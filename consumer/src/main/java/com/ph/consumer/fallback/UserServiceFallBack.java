package com.ph.consumer.fallback;

import com.ph.consumer.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 17:20
 */
@Component
public class UserServiceFallBack implements  UserService{
  @Override
  public String sayHello() {
    return "provider出错啦,无法为你提供服务";
  }
}
