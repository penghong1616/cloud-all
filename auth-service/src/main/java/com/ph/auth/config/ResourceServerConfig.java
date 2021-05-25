package com.ph.auth.config;

import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**@desrciption 资源服务器
 * @author 彭宏
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Qualifier("dataSource")
  @Autowired
  private DataSource dataSource;

  /**
   * 配置用户资源服务器
   * @return
   */
  @Bean
  protected ResourceServerConfiguration userResource(){
   ResourceServerConfiguration resource = new ResourceServerConfiguration() {
      // Switch off the Spring Boot @Autowired configurers
      @Override
      public void setConfigurers(List<ResourceServerConfigurer> configurers) {
        super.setConfigurers(configurers);
      }
    };

    resource.setConfigurers(Arrays.<ResourceServerConfigurer>asList(new ResourceServerConfigurerAdapter() {

      @Override
      public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        TokenStore tokenStore = new JdbcTokenStore(dataSource);
        resources.resourceId("user").tokenStore(tokenStore).stateless(true);
      }

      @Override
      public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
            .and()
            .requestMatchers().antMatchers("/user/**");;
      }


    }));
    resource.setOrder(4);

    return resource;
  }
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
  }

}