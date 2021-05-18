package cn.zhangxd.gateway.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:46
 */

public class UserServcieImpl implements  UserService {

  @Override
  public String sayHello() {
    return "hello,我是你老爹";
  }
}
