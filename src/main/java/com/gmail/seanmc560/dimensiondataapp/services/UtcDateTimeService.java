package com.gmail.seanmc560.dimensiondataapp.services;

import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class UtcDateTimeService implements DateTimeService {

  @Override
  public ZonedDateTime getCurrentDateTime() {
    return ZonedDateTime.now(ZoneOffset.UTC);
  }
}
