package com.showtime.analytics.codingchallenge.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Order(1)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthManager authenticationManager;

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {
    final ApiKeyFilter filter = new ApiKeyFilter();
    filter.setAuthenticationManager(authenticationManager);
    httpSecurity.
        antMatcher("/url/encode")
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
  }

}