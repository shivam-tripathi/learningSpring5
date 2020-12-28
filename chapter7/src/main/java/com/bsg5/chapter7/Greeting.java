package com.bsg5.chapter7;

import java.util.Objects;

public class Greeting {
  String message;

  public Greeting(String message) {
    this.message = message;
  }

  public Greeting() {
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Greeting) {
      return Objects.equals(this.getMessage(), ((Greeting) obj).getMessage());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getMessage());
  }
}
