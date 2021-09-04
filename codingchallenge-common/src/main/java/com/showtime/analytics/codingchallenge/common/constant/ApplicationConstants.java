package com.showtime.analytics.codingchallenge.common.constant;

public class ApplicationConstants {

  public static final String API_ROOT_PATH = "/";

  public static final String API_URL_PATH = "/url";

  public static final String PATH_PARAM_URL = "url";

  public static final String API_PATH_ENCODE = "/encode";

  public static final String API_PATH_DECODE = "/decode";

  public static final String ACCEPTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public static final String INVALID_SHORT_URL_EXCEPTION_MESSAGE = "The requsted short url is invalid or does not exist";

  public static final String GENERIC_UNKNOWN_ERROR_MESSAGE = "An unknown error has occurred";

  public static final String GENERIC_UNKNOWN_ERROR_TRY_AGAIN_MESSAGE = GENERIC_UNKNOWN_ERROR_MESSAGE + " please try again";

  public static final String URL_NOT_FOUND_MESSAGE = "Url could not be found";
}
