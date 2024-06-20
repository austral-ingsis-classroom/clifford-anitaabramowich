package edu.austral.ingsis;

import edu.austral.ingsis.clifford.file.FileSystemCli;
import edu.austral.ingsis.clifford.file.MyFileSystem;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {
  private final FileSystemCli fileSystemCli;

  public FileSystemRunnerImpl() {
    this.fileSystemCli = new FileSystemCli(new MyFileSystem());
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();

    for (String command : commands) {
      String result = fileSystemCli.executeCommand(command);
      results.add(result);
    }

    return results;
  }
}
