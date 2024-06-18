package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.file.Node;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LsCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, List<String> options, List<String> arguments) {
        if (options.size() > 1 || !arguments.isEmpty()) {
            return "Invalid arguments or option";
        }

        Directory currentDirectory = fileSystem.getCurrentDirectory();
        List<String> names = currentDirectory.getChildren()
                .stream()
                .map(Node::getName)
                .collect(Collectors.toList());

        if (options.isEmpty()) {
            return listNames(names);
        }

        String option = options.get(0);

        if (option.startsWith("--ord=")) {
            switch (option.split("=")[1]) {
                case "asc":
                    names.sort(String::compareTo);
                    break;

                case "desc":
                    names.sort(Comparator.reverseOrder());
                    break;

                default:
                    return "Invalid parameter: " + option.split("=")[1];
            }
            return listNames(names);
        }

        return "error";
    }

    private String listNames(List<String> names) {
        return String.join(" ", names);
    }
}