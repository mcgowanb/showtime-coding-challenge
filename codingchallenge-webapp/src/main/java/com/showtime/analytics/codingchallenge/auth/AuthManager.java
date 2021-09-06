package com.showtime.analytics.codingchallenge.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthManager implements AuthenticationManager {

  @Value("${http.auth.api-key}")
  private String principalRequestValue;

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    final String principal = (String) authentication.getPrincipal();
    if (!principalRequestValue.equals(principal)) {
      throw new BadCredentialsException("The API key was not found or not the expected value.");
    }
    authentication.setAuthenticated(true);
    return authentication;
  }
}
