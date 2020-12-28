package com.bsg5.chapter7;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public ArtistNotFoundException(String message) {
    super(message);
    System.out.println(message);
  }
  public ArtistNotFoundException(Exception e) {
    super(e);
  }
}
