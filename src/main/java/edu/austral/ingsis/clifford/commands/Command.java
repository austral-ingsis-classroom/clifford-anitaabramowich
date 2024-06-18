package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.file.FileSystem;

import java.util.List;

public interface Command {
    String execute(FileSystem fileSystem, List<String> options, List<String> arguments);
}