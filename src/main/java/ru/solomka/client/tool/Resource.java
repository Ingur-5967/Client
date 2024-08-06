package ru.solomka.client.tool;

import ru.solomka.client.Client;

import java.io.InputStream;
import java.net.URL;

public class Resource {

    public static InputStream getFileAsStream(String absolutePath) {
        return Client.getInstance().getClass().getResourceAsStream(absolutePath);
    }

    public static URL getFileAsResource(String absolutePath) {
        return Client.getInstance().getClass().getResource(absolutePath);
    }
}