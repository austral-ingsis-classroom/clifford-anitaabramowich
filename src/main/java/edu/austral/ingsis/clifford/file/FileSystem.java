package edu.austral.ingsis.clifford.file;

public interface FileSystem {

  Directory getCurrentDirectory();

  Directory getRootDirectory();

  void setCurrentDirectory(Directory directory);
}
