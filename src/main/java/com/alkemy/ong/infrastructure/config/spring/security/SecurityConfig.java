package com.alkemy.ong.infrastructure.config.spring.security;

import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.config.spring.security.filter.AuthorizationFilter;
import com.alkemy.ong.infrastructure.config.spring.security.filter.CustomAccessDeniedHandler;
import com.alkemy.ong.infrastructure.config.spring.security.filter.CustomAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthorizationFilter authorizationFilter;

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthEntryPoint();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.DELETE, "/users/{id:[\\d+]}")
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/slides/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.PUT,"/categories/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/categories/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/members/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/testimonials/{id:[\\d+]}")
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/organizations/public")
        .permitAll()
        .antMatchers(HttpMethod.DELETE, "/comments/{id:[\\d+]}")
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/news/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.POST,  "/auth/register")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
  }

}