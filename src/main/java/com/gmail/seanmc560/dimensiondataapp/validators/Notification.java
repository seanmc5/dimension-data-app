package com.gmail.seanmc560.dimensiondataapp.validators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Notification {

  private List<String> errors = new ArrayList<>();

  public static Notification withError(final String message) {
    final Notification notification = new Notification();
    notification.addError(message);

    return notification;
  }

  public static Notification empty() {
    return new Notification();
  }

  public void addError(String message) {
    errors.add(message);
  }

  public String errorMessage() {
    return String.join(", ", errors);
  }

  public boolean hasErrors() {
    return !errors.isEmpty();
  }
}
