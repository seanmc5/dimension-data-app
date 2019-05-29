package com.gmail.seanmc560.dimensiondataapp.responses;

import lombok.Builder;

@Builder
public class ServerResponse {

  private final Long id;
  private final String name;
  private final String description;
  private final String createdDate;
  private final String lastUpdatedDate;

  @Override
  public String toString() {
    return String.format(
      "id: %d, name: %s, description: %s, createdDate: %s, lastUpdatedDate: %s",
      id,
      name,
      description,
      createdDate,
      lastUpdatedDate
    );
  }
}
