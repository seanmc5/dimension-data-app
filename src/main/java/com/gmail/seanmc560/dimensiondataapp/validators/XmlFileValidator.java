package com.gmail.seanmc560.dimensiondataapp.validators;

import com.gmail.seanmc560.dimensiondataapp.utils.FileUtils;
import com.gmail.seanmc560.dimensiondataapp.xml.ServerXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class XmlFileValidator implements Validator<String> {

  private final FileUtils fileUtils;

  @Override
  public Notification validate(final String fileName) {
    if (fileName.isEmpty() || !fileName.endsWith(".xml")) {
      return Notification.withError("Invalid file name");
    }

    final File file = new File(fileName);
    if (!file.exists() || file.isDirectory()) {
      return Notification.withError("File does not exist or is not a file");
    }

    if (!containsValidXml(fileName)) {
      return Notification.withError("File does not contain valid xml");
    }

    return Notification.empty();
  }

  private boolean containsValidXml(final String fileName) {
    try {
      fileUtils.xmlFileToObject(fileName, ServerXml.class);
      return true;
    } catch (final Exception ignored) {
    }
    return false;
  }
}
