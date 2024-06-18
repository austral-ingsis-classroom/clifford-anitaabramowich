package edu.austral.ingsis;

import edu.austral.ingsis.clifford.file.FileSystemClient;
import edu.austral.ingsis.clifford.file.MyFileSystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {
    private final FileSystemClient fileSystemClient;

    public FileSystemRunnerImpl() {
        this.fileSystemClient = new FileSystemClient(new MyFileSystem());
    }

    @Override
    public List<String> executeCommands(List<String> commands) {
        List<String> results = new ArrayList<>();

        for (String command : commands) {
            String result = fileSystemClient.executeCommand(command);
            results.add(result);
        }

        return results;
    }
}
