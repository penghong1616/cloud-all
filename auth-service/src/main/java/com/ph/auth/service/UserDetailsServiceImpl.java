package com.ph.auth.service;

import com.ph.pojo.Role;
import com.ph.pojo.User;
import com.ph.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 9:44
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  UserRepository userRepository;
  //@Qualifier("passwordEncoder")
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("通过用户名查找用户：{}", username);
    //User user=userRepository.findByUsername(username);
    String password = passwordEncoder.encode("admin");
    username = "admin";
    logger.info("密码：{}", password);
    List<Role> authorities = new ArrayList<Role>();
    authorities.add(new Role(1L, "ROLE_ADMIN"));
    authorities.add(new Role(2L, "ROLE_USER"));
    return new User(username, password, authorities);
  }
}
