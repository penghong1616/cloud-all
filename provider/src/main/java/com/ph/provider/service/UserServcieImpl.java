package com.ph.provider.service;

import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:46
 */
@Service
public class UserServcieImpl implements  UserService {

  @Override
  public String sayHello() {
    return "hello,我是你老爹";
  }
}
