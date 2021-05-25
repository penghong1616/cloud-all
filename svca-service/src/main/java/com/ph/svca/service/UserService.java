package com.ph.svca.service;

import com.ph.pojo.User;
import com.ph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 13:19
 */
@Service
public class UserService {
  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Autowired
  private UserRepository userRepository;

  public User create(String username, String password) {
    User user = new User();
    user.setUsername(username);
    String hash = encoder.encode(password);
    user.setPassword(hash);
    return userRepository.save(user);
  }
}