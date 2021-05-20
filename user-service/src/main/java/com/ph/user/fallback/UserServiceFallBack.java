package com.ph.user.fallback;

import com.ph.user.pojo.User;
import com.ph.user.service.UserService;
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

  @Override
  public User login(User user) {
    return null;
  }

  @Override
  public String buy(User user) {
    return null;
  }
}
