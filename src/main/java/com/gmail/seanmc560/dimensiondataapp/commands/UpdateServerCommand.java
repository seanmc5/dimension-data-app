package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerResponse;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import com.gmail.seanmc560.dimensiondataapp.transformers.ServerEntityToServerResponseTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class UpdateServerCommand {

  private final ServerService serverService;
  private final ServerEntityToServerResponseTransformer serverEntityToServerResponseTransformer;

  @ShellMethod("Updates a server")
  public String updateServer(@ShellOption final Long id, @ShellOption final String name, @ShellOption final String description) {

    if (!serverService.exists(id)) {
      return Response.notFound();
    }

    final ServerEntity serverEntity = serverService.update(id, name, description);
    final ServerResponse serverResponse = serverEntityToServerResponseTransformer.transform(serverEntity);

    return Response.updated(serverResponse.toString());
  }
}
