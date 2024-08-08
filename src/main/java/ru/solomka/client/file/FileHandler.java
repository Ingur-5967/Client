package ru.solomka.client.file;

import ru.solomka.client.AppLoader;
import ru.solomka.client.file.entity.FileEntry;

import java.io.IOException;
import java.util.List;

public class FileHandler {


    public boolean createFile(String path, String file) {
        return false;
    }

    public List<FileEntry> getDirectoryContent(String path) {
        return null;
    }

    public FileEntry getFile(String path, String file) {
        return null;
    }

    public void writeToFile(FileEntry file, List<String> source) {

    }

    public List<String> readFromFile() {
        return null;
    }
}
