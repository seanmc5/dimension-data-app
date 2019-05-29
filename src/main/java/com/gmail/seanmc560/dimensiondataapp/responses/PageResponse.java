package com.gmail.seanmc560.dimensiondataapp.responses;

import lombok.Builder;

@Builder
public class PageResponse {
  private final Integer size;
  private final Integer totalElements;
  private final Integer totalPages;
  private final Integer number;

  @Override
  public String toString() {
    return String.format(
      "size: %d, totalElements: %d, totalPages: %d, number: %d",
      size,
      totalElements,
      totalPages,
      number
    );
  }
}
