package com.ph.auth.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class ResourceServerConfig {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    /**
     * 配置卖书的资源服务器
     *
     * @return
     */
    @Bean
    protected ResourceServerConfiguration bookResources() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
            @Override
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };

        resource.setConfigurers(Collections.<ResourceServerConfigurer>singletonList(
            new ResourceServerConfigurerAdapter() {

                @Override
                public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                    TokenStore tokenStore = new JdbcTokenStore(dataSource);
                    resources.resourceId("book").tokenStore(tokenStore).stateless(true);
                }

                @Override
                public void configure(HttpSecurity http) throws Exception {
                    http.authorizeRequests().anyRequest().authenticated()
                        .and()
                        .requestMatchers().antMatchers("/book/**");
                    ;
                }

            }));
        resource.setOrder(3);

        return resource;

    }

    /**
     * 配置卖鸡蛋的资源服务器
     *
     * @return
     */
    @Bean
    protected ResourceServerConfiguration eggResources() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };

        resource.setConfigurers(Collections.<ResourceServerConfigurer>singletonList(
            new ResourceServerConfigurerAdapter() {

                @Override
                public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                    TokenStore tokenStore = new JdbcTokenStore(dataSource);
                    resources.resourceId("egg").tokenStore(tokenStore).stateless(true);
                }

                @Override
                public void configure(HttpSecurity http) throws Exception {
                    http.authorizeRequests().anyRequest().authenticated()
                        .and()
                        .requestMatchers().antMatchers("/egg/**");
                }

            }));
        resource.setOrder(4);

        return resource;

    }
    /**
     * 配置用户信息的资源服务器
     */
    @Bean
    protected ResourceServerConfiguration userInfoResources() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
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
                    .requestMatchers().antMatchers("/user/**");
            }

        }));
        resource.setOrder(5);

        return resource;

    }

}