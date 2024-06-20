package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.File;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.List;
import java.util.Optional;

public class TouchCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
    if (arguments.size() != 1) {
      return "Invalid arguments";
    }

    String fileName = arguments.get(0);

    if (fileName.contains("/") || fileName.contains(" ")) {
      return "Invalid file name: " + fileName;
    }

    Directory currentDirectory = fileSystem.getCurrentDirectory();

    boolean fileExists =
        currentDirectory.getChildren().stream().anyMatch(node -> node.getName().equals(fileName));

    if (fileExists) {
      return "File already exists: " + fileName;
    }

    File newFile = new File(fileName, Optional.of(currentDirectory));
    currentDirectory.addChild(newFile);

    return "'" + fileName + "' file created";
  }
}
