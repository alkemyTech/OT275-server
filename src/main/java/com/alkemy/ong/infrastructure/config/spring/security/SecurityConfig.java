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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String AUTH_REGISTER_URL = "/auth/register";
  private static final String AUTH_LOGIN_URL = "/auth/login";
  private static final String ORGANIZATION_PUBLIC_URL = "/organization/public";
  private static final String COMMENTS_URL = "/comments";
  private static final String COMMENTS_ID_URL = "/comments/{id:[\\d+]}";
  private static final String SLIDES_URL = "/slides";
  private static final String NEWS_ID_URL = "/news/{id:[\\d+]}";
  private static final String NEWS_URL = "/news";
  private static final String SLIDES_ID_URL = "/slides/{id:[\\d+]}";
  private static final String CATEGORIES_ID_URL = "/categories/{id:[\\d+]}";
  private static final String CATEGORIES_URL = "/categories";
  private static final String ACTIVITIES_ID_URL = "/activities/{id:[\\d+]}";
  private static final String USERS_ID_URL = "/users/{id:[\\d+]}";
  private static final String MEMBERS_ID_URL = "/members/{id:[\\d+]}";
  private static final String TESTIMONIALS_ID_URL = "/testimonials/{id:[\\d+]}";
  private static final String ACTIVITIES_URL = "/activities";
  private static final String USERS_URL = "/users";
  private static final String NEWS_WITH_COMMENTS_URL = "/news/{id:[\\d+]}/comments";
  private static final String CONTACTS_URL = "/contacts";

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

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

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
    managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
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
        .antMatchers(HttpMethod.POST, AUTH_REGISTER_URL)
        .permitAll()
        .antMatchers(HttpMethod.POST, AUTH_LOGIN_URL)
        .permitAll()
        .antMatchers(HttpMethod.GET, ORGANIZATION_PUBLIC_URL)
        .permitAll()
        .antMatchers(HttpMethod.PATCH, ORGANIZATION_PUBLIC_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, USERS_ID_URL)
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, SLIDES_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.PUT, CATEGORIES_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, CATEGORIES_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, MEMBERS_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, TESTIMONIALS_ID_URL)
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.POST, COMMENTS_URL)
        .hasRole(Role.USER.name())
        .antMatchers(HttpMethod.GET, COMMENTS_URL)
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.DELETE, COMMENTS_ID_URL)
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, NEWS_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, NEWS_ID_URL)
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.POST, NEWS_URL)
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, SLIDES_URL)
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.GET, CATEGORIES_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.POST, CATEGORIES_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, CATEGORIES_ID_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, SLIDES_ID_URL)
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.POST, SLIDES_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.POST, ACTIVITIES_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, USERS_URL)
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.PUT, ACTIVITIES_ID_URL)
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.PUT, USERS_ID_URL)
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, NEWS_WITH_COMMENTS_URL)
        .hasRole(Role.USER.name())
        .antMatchers(HttpMethod.GET, CONTACTS_URL)
        .hasAnyRole(Role.ADMIN.name())
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
  }

}