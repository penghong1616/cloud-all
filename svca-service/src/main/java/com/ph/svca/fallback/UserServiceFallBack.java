package com.ph.svca.fallback;

import com.ph.svca.service.UserService;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 17:20
 */
//@Component
public class UserServiceFallBack implements UserService {
  @Override
  public String info() {
    return "业务忙，稍后获取";
  }
}
