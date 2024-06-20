package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.file.Node;
import java.util.List;
import java.util.Optional;

public class RmCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
    if (arguments.size() != 1) {
      return "Invalid arguments";
    }

    String target = arguments.get(0);
    boolean recursive = options.contains("--recursive");

    Directory currentDirectory = fileSystem.getCurrentDirectory();
    Optional<Node> nodeToRemove =
        currentDirectory.getChildren().stream()
            .filter(node -> node.getName().equals(target))
            .findFirst();

    if (nodeToRemove.isEmpty()) {
      return "'" + target + "' does not exist";
    }

    Node node = nodeToRemove.get();

    if (node instanceof Directory && !recursive) {
      return "cannot remove '" + target + "', is a directory";
    }

    if (node instanceof Directory) {
      currentDirectory.removeChild(node);
      return "'" + target + "' removed";
    } else {
      currentDirectory.removeChild(node);
      return "'" + target + "' removed";
    }
  }
}
