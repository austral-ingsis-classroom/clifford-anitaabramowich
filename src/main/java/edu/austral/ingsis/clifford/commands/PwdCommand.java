package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.ArrayList;
import java.util.List;

public class PwdCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
    Directory currentDirectory = fileSystem.getCurrentDirectory();
    return getPath(currentDirectory);
  }

  private String getPath(Directory directory) {
    if (directory == null) {
      return "/";
    }

    List<String> pathElements = new ArrayList<>();
    while (directory != null) {
      if (!directory.getName().equals("/")) {
        pathElements.add(0, directory.getName());
      }
      directory = directory.getParent().orElse(null);
    }

    return "/" + String.join("/", pathElements);
  }
}
