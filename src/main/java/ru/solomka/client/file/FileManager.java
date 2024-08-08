package ru.solomka.client.file;

import java.io.File;

public class FileManager {

    public void createDirectoryByPath(String path) {
        File source = new File(path);
        source.mkdir();
    }

    public boolean existsPath(String path) {
        return false;
    }
}