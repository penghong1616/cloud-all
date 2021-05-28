package com.ph.svca.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.util.Date;
import org.apache.http.HttpStatus;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * @Description: Feign调用HTTP返回响应码错误时候，定制错误的解码
 * @author: penghong
 * @date: 2021/05/27/ 15:48
 */
class RestClientErrorDecoder implements ErrorDecoder {
  private org.slf4j.Logger logger = LoggerFactory
      .getLogger(RestClientErrorDecoder.class);

  private OAuth2ClientContext context;

  RestClientErrorDecoder(OAuth2ClientContext context) {
    this.context = context;
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    logger.error("Feign调用异常，异常methodKey:{}, token:{}, response:{}", methodKey, context.getAccessToken(), response.body());
    if (HttpStatus.SC_UNAUTHORIZED == response.status()) {
      logger.error("接收到Feign请求资源响应401，access_token已经过期，重置access_token为null待重新获取。");
      context.setAccessToken(null);
      return new RetryableException("疑似access_token过期，即将进行重试", new Date());
    }
    return errorStatus(methodKey, response);
  }

  private Exception errorStatus(String methodKey, Response response) {
    return new Exception(methodKey,new Throwable(response.reason()));
  }
}
