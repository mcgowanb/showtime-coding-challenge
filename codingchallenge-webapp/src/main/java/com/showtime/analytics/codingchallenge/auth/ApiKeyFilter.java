package com.showtime.analytics.codingchallenge.auth;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.API_KEY_HEADER_NAME;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ApiKeyFilter extends AbstractPreAuthenticatedProcessingFilter {

  @Override
  protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
    return request.getHeader(API_KEY_HEADER_NAME);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
    return "N/A";
  }
}
