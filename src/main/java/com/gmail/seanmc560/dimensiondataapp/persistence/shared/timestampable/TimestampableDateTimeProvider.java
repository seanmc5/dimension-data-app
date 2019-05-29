package com.gmail.seanmc560.dimensiondataapp.persistence.shared.timestampable;

import com.gmail.seanmc560.dimensiondataapp.services.DateTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@RequiredArgsConstructor
public class TimestampableDateTimeProvider implements DateTimeProvider {

  private final DateTimeService dateTimeService;

  @Override
  public Optional<TemporalAccessor> getNow(){
    return Optional.of(dateTimeService.getCurrentDateTime());
  }
}
