package cn.zhangxd.gateway.fallback;

import cn.zhangxd.gateway.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/18/ 17:20
 */
@Service
public class UserServiceFallBack implements UserService {

  @Override
  public String sayHello() {
    return "出错啦";
  }
}
