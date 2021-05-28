package com.ph.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/27/ 10:09
 */
@Configuration
public class InitBeanConfig {
  @Bean
  public PasswordEncoder initPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
