package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerListResponse;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import com.gmail.seanmc560.dimensiondataapp.transformers.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Min;

@ShellComponent
@RequiredArgsConstructor
public class ListServersCommands {

  private final Transformer<Page<ServerEntity>, ServerListResponse> pageServerToListResponseTransformer;
  private final ServerService serverService;

  @ShellMethod("List servers")
  public String listServers(
    @ShellOption(defaultValue = "") final String nameContaining,
    @ShellOption(defaultValue = "") final String descriptionContaining,
    @ShellOption(defaultValue = "0") @Min(value = 0) final Integer page,
    @ShellOption(defaultValue = "20") @Min(value = 1)final Integer size
    ) {

    final Page<ServerEntity> serverPage = serverService.findAllByNameContainingAndDescriptionContaining(
      nameContaining,
      descriptionContaining,
      PageRequest.of(page, size)
    );

    final ServerListResponse serverListResponse =  pageServerToListResponseTransformer.transform(serverPage);

    return Response.ok(serverListResponse.toString());
  }
}
