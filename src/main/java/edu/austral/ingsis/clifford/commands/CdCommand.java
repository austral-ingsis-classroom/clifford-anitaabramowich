package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.file.Node;
import java.util.List;

public class CdCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
    if (arguments.size() != 1) {
      return "Invalid arguments";
    }

    String target = arguments.get(0);

    Directory currentDirectory = fileSystem.getCurrentDirectory();

    if (target.equals("/")) {
      fileSystem.setCurrentDirectory(currentDirectory.getRoot());
      return "moved to directory '" + currentDirectory.getRoot().getName() + "'";
    }

    String[] directories = target.split("/");

    for (String dir : directories) {
      currentDirectory = navigateToDirectory(currentDirectory, dir);
      if (currentDirectory == null) {
        return "'" + dir + "' directory does not exist";
      }
    }

    fileSystem.setCurrentDirectory(currentDirectory);

    return "moved to directory '" + currentDirectory.getName() + "'";
  }

  private Directory navigateToDirectory(Directory currentDirectory, String target) {
    if (target.equals(".")) {
      return currentDirectory;
    } else if (target.equals("..")) {
      if (currentDirectory.getName().equals("/")) {
        return currentDirectory;
      } else {
        Directory parent = currentDirectory.getParent().orElse(null);
        return parent;
      }
    } else if (target.equals("/")) {
      return currentDirectory.getRoot();
    } else {
      for (Node child : currentDirectory.getChildren()) {
        if (child instanceof Directory && child.getName().equals(target)) {
          return (Directory) child;
        }
      }
      return null;
    }
  }
}
