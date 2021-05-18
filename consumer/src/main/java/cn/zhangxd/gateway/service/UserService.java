package cn.zhangxd.gateway.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 16:45
 */
@FeignClient(value = "provider-service",fallback = UserServcieImpl.class)
public interface UserService {
  String sayHello();

}
