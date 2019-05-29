package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class CountServersCommand {

  private final ServerService serverService;

  @ShellMethod("Count servers")
  public String countServers() {
    final Long serverCount = serverService.count();
    final String responseMessage = String.format("Count: %d", serverCount);

    return Response.ok(responseMessage);
  }
}
