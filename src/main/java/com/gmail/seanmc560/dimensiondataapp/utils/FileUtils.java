package com.gmail.seanmc560.dimensiondataapp.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class FileUtils {

  @SneakyThrows
  public <T> T xmlFileToObject(final String fileName, final Class<T> type) {

    final String fileContentAsString = Files.newBufferedReader(Paths.get(fileName))
      .lines()
      .collect(Collectors.joining());

    return new XmlMapper().readValue(fileContentAsString, type);
  }
}
