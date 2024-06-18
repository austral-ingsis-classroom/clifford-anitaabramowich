package edu.austral.ingsis.clifford.file;

import java.util.Optional;

public interface Node {
    String getName();
    Optional<Directory>  getParent();
}