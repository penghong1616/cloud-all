package cn.zhangxd.gateway.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
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
