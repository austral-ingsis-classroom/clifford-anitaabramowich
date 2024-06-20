package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.List;
import java.util.Optional;

public class MkdirCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
    if (arguments.size() != 1) {
      return "Invalid argument";
    }

    String directoryName = arguments.get(0);

    if (!isValidDirectoryName(directoryName)) {
      return "Invalid directory name";
    }

    Directory currentDirectory = fileSystem.getCurrentDirectory();

    Directory newDirectory = new Directory(directoryName, Optional.of(currentDirectory));
    currentDirectory.addChild(newDirectory);

    return "'" + directoryName + "' directory created";
  }

  private boolean isValidDirectoryName(String name) {
    return !(name.contains("/") || name.contains(" "));
  }
}
