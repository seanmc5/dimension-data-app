package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Min;

@ShellComponent
@RequiredArgsConstructor
public class DeleteServerCommand {

  private final ServerService serverService;

  @ShellMethod("Deletes a server")
  public String deleteServer(@Min(value = 1) final Long id) {

    if(!serverService.exists(id)){
      return Response.notFound();
    }

    serverService.delete(id);

    return Response.deleted();
  }
}
