package edu.austral.ingsis.clifford.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Directory implements Node {
    private List<Node> children;
    private String name;
    private Optional<Directory> parent;

    public Directory(String name, Optional<Directory> parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<Directory> getParent() {
        return this.parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

}