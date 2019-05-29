package com.gmail.seanmc560.dimensiondataapp.transformers;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerResponse;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class ServerEntityToServerResponseTransformer implements Transformer<ServerEntity, ServerResponse> {

  public ServerResponse transform(final ServerEntity serverEntity) {
    return ServerResponse.builder()
      .id(serverEntity.getId())
      .name(serverEntity.getName())
      .description(serverEntity.getDescription())
      .createdDate(toUtcIso8601String(serverEntity.getCreatedDate()))
      .lastUpdatedDate(toUtcIso8601String(serverEntity.getLastUpdatedDate()))
      .build();
  }

  private String toUtcIso8601String(final ZonedDateTime zonedDateTime) {
    return Optional.ofNullable(zonedDateTime)
      .map(z -> z.withZoneSameInstant(ZoneOffset.UTC))
      .map(z -> z.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
      .orElse(null);
  }
}
