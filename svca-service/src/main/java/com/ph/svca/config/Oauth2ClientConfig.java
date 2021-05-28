package com.ph.svca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

/**
 * @Description: TODO
 * @author: penghong
 * @date: 2021/05/27/ 17:10
 */
@Configuration
public class Oauth2ClientConfig {
  @Bean("paascloudClientCredentialsResourceDetails")
  public ClientCredentialsResourceDetails resourceDetails() {
    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
    details.setId("svca");
    details.setAccessTokenUri("http://localhost:84/oauth/token");
    details.setClientId("svca");
    details.setClientSecret("svca");
    details.setAuthenticationScheme(AuthenticationScheme.valueOf("header"));
    return details;
  }

  @Bean("paascloudOAuth2RestTemplate")
  public OAuth2RestTemplate oAuth2RestTemplate() {
    final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
    oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());

    return oAuth2RestTemplate;

  }
}
