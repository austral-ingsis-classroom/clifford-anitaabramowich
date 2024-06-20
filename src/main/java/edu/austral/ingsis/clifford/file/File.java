package edu.austral.ingsis.clifford.file;

import java.util.Optional;

public class File implements Node {
  private String name;
  private Optional<Directory> parent;

  public File(String name, Optional<Directory> parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Optional<Directory> getParent() {
    return this.parent;
  }
}
