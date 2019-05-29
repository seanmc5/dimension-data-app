package com.gmail.seanmc560.dimensiondataapp.responses;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class ServerListResponse {

  private final PageResponse page;
  private final List<ServerResponse> servers;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(" --- Servers --- ");
    sb.append("\n");

    final String serversAsString = servers.stream()
      .map(ServerResponse::toString)
      .collect(Collectors.joining("\n"));

    sb.append(serversAsString);
    sb.append("\n");

    sb.append(" --- Page Info --- ");
    sb.append("\n");

    sb.append(page.toString());

    return sb.toString();
  }
}
