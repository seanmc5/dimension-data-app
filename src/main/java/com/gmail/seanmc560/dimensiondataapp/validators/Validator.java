package com.gmail.seanmc560.dimensiondataapp.validators;

public interface Validator<T> {

  Notification validate(T obj);
}
