package com.ph.svca.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/21/ 13:21
 */
@RestController
public class HiController {
  private static final Logger LOGGER = LoggerFactory.getLogger(HiController.class);

  @Value("${server.port}")
  private String port;

  /**
   * 不需要任何权限，只要Header中的Token正确即可
   */
  @RequestMapping("/hi")
  public String hi() {
    return "hi : " + ",i am from port: " + port;
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello you!";
  }

  /**
   * 获取当前认证用户的信息
   */
  @GetMapping("/getPrinciple")
  public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication,
      Principal principal,
      Authentication authentication){
    LOGGER.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
    LOGGER.info(oAuth2Authentication.toString());
    LOGGER.info("principal.toString()" + principal.toString());
    LOGGER.info("principal.getName()" + principal.getName());
    LOGGER.info("authentication:" + authentication.getAuthorities().toString());

    return oAuth2Authentication;
  }
}