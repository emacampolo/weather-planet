package com.campolo.weather.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public class HttpApiError {

  private HttpStatus status;

  @JsonInclude(Include.NON_EMPTY)
  private String message;

  @JsonInclude(Include.NON_EMPTY)
  private List<String> errors;

  private HttpApiError(final HttpStatus status, final String message, final List<String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public static HttpApiError create(final HttpStatus status) {
    return create(status, null);
  }

  public static HttpApiError create(final HttpStatus status, final String message) {
    return createWithErrors(status, message, Collections.emptyList());
  }

  public static HttpApiError createWithErrors(
      final HttpStatus status, final String message, final String error) {
    return createWithErrors(status, message, Collections.singletonList(error));
  }

  public static HttpApiError createWithErrors(
      final HttpStatus status, final String message, final List<String> errors) {
    return new HttpApiError(status, message, errors);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }
}
