package com.gmail.seanmc560.dimensiondataapp.persistence.shared.timestampable;

import com.gmail.seanmc560.dimensiondataapp.services.DateTimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class TimestampableConfig {

  @Bean
  public DateTimeProvider dateTimeProvider(final DateTimeService dateTimeService){
    return new TimestampableDateTimeProvider(dateTimeService);
  }
}
