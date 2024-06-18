package edu.austral.ingsis.clifford.file;

import edu.austral.ingsis.clifford.commands.CdCommand;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.LsCommand;
import edu.austral.ingsis.clifford.commands.MkdirCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSystemClient {
    private final FileSystem fileSystem;
    private final Map<String, Command> commands = new HashMap<>();

    public FileSystemClient(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("mkdir", new MkdirCommand());
    }

    public String executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        String commandName = parts[0].toLowerCase();
        Command command = commands.get(commandName);

        if (command == null) {
            return "Unknown command: " + commandName;
        }

        List<String> options = Arrays.stream(parts)
                .filter(part -> part.startsWith("--"))
                .collect(Collectors.toList());

        List<String> arguments = Arrays.stream(parts)
                .filter(part -> !part.startsWith("--"))
                .skip(1)
                .collect(Collectors.toList());

        return command.execute(fileSystem, options, arguments);
    }
}
