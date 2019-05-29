package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerResponse;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import com.gmail.seanmc560.dimensiondataapp.transformers.ServerEntityToServerResponseTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotEmpty;

@ShellComponent
@RequiredArgsConstructor
public class CreateServerCommand {

  private final ServerService serverService;
  private final ServerEntityToServerResponseTransformer serverEntityToServerResponseTransformer;

  @ShellMethod("Creates a server")
  public String createServer(@NotEmpty final String name, @NotEmpty final String description) {

    final ServerEntity savedServerEntity = serverService.save(name, description);
    final ServerResponse serverResponse = serverEntityToServerResponseTransformer.transform(savedServerEntity);

    return Response.created(serverResponse.toString());
  }
}
