package com.ph.svca.config;

import feign.FeignException;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.Util;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @Description: 自定义响应
 * @author: penghong
 * @date: 2021/05/27/ 15:40
 */
//@Configuration
public class OAuth2FeignConfigure {
  // feign的OAuth2ClientContext
  private OAuth2ClientContext feignOAuth2ClientContext =  new DefaultOAuth2ClientContext();

  @Resource
  private ClientCredentialsResourceDetails clientCredentialsResourceDetails;

  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;

  @Bean
  public OAuth2RestTemplate clientCredentialsRestTemplate(){
    return new OAuth2RestTemplate(clientCredentialsResourceDetails);
  }

  @Bean
  public RequestInterceptor oauth2FeignRequestInterceptor(){
    return new OAuth2FeignRequestInterceptor(feignOAuth2ClientContext, clientCredentialsResourceDetails);
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }


  @Bean
  public Retryer retry() {
    // default Retryer will retry 5 times waiting waiting
    // 100 ms per retry with a 1.5* back off multiplier
    return new Retryer.Default(100, 1000, 3);
  }


  @Bean
  public Decoder feignDecoder() {
    return new CustomResponseEntityDecoder(new SpringDecoder(this.messageConverters), feignOAuth2ClientContext);
  }


  /**
   *  Http响应成功 但是token失效，需要定制 ResponseEntityDecoder
   * @author maxianming
   * @date 2018/10/30 9:47
   */
  class CustomResponseEntityDecoder implements Decoder {
    private org.slf4j.Logger log = LoggerFactory.getLogger(CustomResponseEntityDecoder.class);

    private Decoder decoder;

    private OAuth2ClientContext context;

    public CustomResponseEntityDecoder(Decoder decoder, OAuth2ClientContext context) {
      this.decoder = decoder;
      this.context = context;
    }

    @Override
    public Object decode(final Response response, Type type) throws IOException, FeignException {
      if (log.isDebugEnabled()) {
        log.debug("feign decode type:{}，reponse:{}", type, response.body());
      }
      if (isParameterizeHttpEntity(type)) {
        type = ((ParameterizedType) type).getActualTypeArguments()[0];
        Object decodedObject = decoder.decode(response, type);
        return createResponse(decodedObject, response);
      }
      else if (isHttpEntity(type)) {
        return createResponse(null, response);
      }
      else {
        // custom ResponseEntityDecoder if token is valid then go to errorDecoder
        String body = Util.toString(response.body().asReader());
        if (body.contains("401")) {
          clearTokenAndRetry(response, body);
        }
        return decoder.decode(response, type);
      }
    }

    /**
     * token失效 则将token设置为null 然后重试
     * @author maxianming
     * @param
     * @return
     * @date 2018/10/30 10:05
     */
    private void clearTokenAndRetry(Response response, String body) throws FeignException {
      log.error("接收到Feign请求资源响应,响应内容:{}",body);
      context.setAccessToken(null);
      throw new RetryableException("access_token过期，即将进行重试", new Date());
    }

    private boolean isParameterizeHttpEntity(Type type) {
      if (type instanceof ParameterizedType) {
        return isHttpEntity(((ParameterizedType) type).getRawType());
      }
      return false;
    }

    private boolean isHttpEntity(Type type) {
      if (type instanceof Class) {
        Class c = (Class) type;
        return HttpEntity.class.isAssignableFrom(c);
      }
      return false;
    }

    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> createResponse(Object instance, Response response) {

      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
      for (String key : response.headers().keySet()) {
        headers.put(key, new LinkedList<>(response.headers().get(key)));
      }
      return new ResponseEntity<>((T) instance, headers, org.springframework.http.HttpStatus.valueOf(response
          .status()));
    }
  }



  @Bean
  public ErrorDecoder errorDecoder() {
    return new RestClientErrorDecoder(feignOAuth2ClientContext);
  }

}