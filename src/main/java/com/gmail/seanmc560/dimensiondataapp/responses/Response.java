package com.gmail.seanmc560.dimensiondataapp.responses;

public class Response {

  public static String created(final String message) {
    return String.format("--- Created ---\n%s", message);
  }

  public static String updated(final String message) {
    return String.format("--- Updated ---\n%s", message);
  }

  public static String deleted() {
    return "--- Deleted ---";
  }

  public static String ok(final String message){
    return String.format("--- OK ---\n%s", message);
  }

  public static String notFound(){
    return "--- Not Found ---";
  }

  public static String badFile(final String message){
    return String.format("--- Bad File ---\n%s", message);
  }
}
