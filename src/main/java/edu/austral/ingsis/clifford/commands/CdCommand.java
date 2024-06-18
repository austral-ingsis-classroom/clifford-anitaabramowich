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

        Directory newDirectory = navigateToDirectory(currentDirectory, target);

        if (newDirectory == null) {
            return "'" + target + "' directory does not exist";
        }

        fileSystem.setCurrentDirectory(newDirectory);

        return "moved to directory '" + newDirectory.getName() + "'";
    }

    private Directory navigateToDirectory(Directory currentDirectory, String target) {
        if (target.equals(".")) {
            return currentDirectory;
        } else if (target.equals("..")) {
            if (currentDirectory.getName().equals("/")){
                return currentDirectory;
            } else{
                Directory parent = currentDirectory.getParent().orElse(null);
                return parent;
            }

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
