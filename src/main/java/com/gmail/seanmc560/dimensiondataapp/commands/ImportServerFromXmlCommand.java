package com.gmail.seanmc560.dimensiondataapp.commands;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.Response;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerResponse;
import com.gmail.seanmc560.dimensiondataapp.services.ServerService;
import com.gmail.seanmc560.dimensiondataapp.transformers.ServerEntityToServerResponseTransformer;
import com.gmail.seanmc560.dimensiondataapp.utils.FileUtils;
import com.gmail.seanmc560.dimensiondataapp.validators.Notification;
import com.gmail.seanmc560.dimensiondataapp.validators.Validator;
import com.gmail.seanmc560.dimensiondataapp.xml.ServerXml;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotEmpty;

@ShellComponent
@RequiredArgsConstructor
public class ImportServerFromXmlCommand {

  private final Validator<String> xmlFileValidator;
  private final FileUtils fileUtils;
  private final ServerService serverService;
  private final ServerEntityToServerResponseTransformer serverEntityToServerResponseTransformer;

  @SneakyThrows
  @ShellMethod("Import server from xml file")
  public String importServerFromXmlFile(@NotEmpty final String fileName) {

    final Notification validationResult = xmlFileValidator.validate(fileName);
    if(validationResult.hasErrors()){
      return Response.badFile(validationResult.errorMessage());
    }

    final ServerXml serverXml = fileUtils.xmlFileToObject(fileName, ServerXml.class);
    final ServerEntity serverEntity = serverService.save(serverXml.getName(), serverXml.getDescription());
    final ServerResponse serverResponse = serverEntityToServerResponseTransformer.transform(serverEntity);

    return Response.created(serverResponse.toString());
  }
}
