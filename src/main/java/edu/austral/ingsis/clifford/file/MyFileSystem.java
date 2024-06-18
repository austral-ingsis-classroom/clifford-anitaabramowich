package edu.austral.ingsis.clifford.file;

import java.util.Optional;

public class MyFileSystem implements FileSystem {

    private Directory rootDirectory;
    private Directory currentDirectory;

    public MyFileSystem() {
        this.rootDirectory = new Directory("/", Optional.empty());
        this.currentDirectory = this.rootDirectory;
    }

    @Override
    public Directory getRootDirectory() {
        return this.rootDirectory;
    }

    @Override
    public Directory getCurrentDirectory() {
        return this.currentDirectory;
    }

    @Override
    public void setCurrentDirectory(Directory directory) {
        this.currentDirectory = directory;
    }
}

