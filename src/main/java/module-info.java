module ru.solomka.client {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.net.http;
    requires java.sql;
    requires MaterialFX;
    requires com.h2database;
    requires lombok;
    requires annotations;
    requires java.naming;

    opens ru.solomka.client to javafx.fxml;
    exports ru.solomka.client;

    exports ru.solomka.client.core.component;
    opens ru.solomka.client.core.component to javafx.fxml;

    exports ru.solomka.client.core;
    opens ru.solomka.client.core to javafx.fxml;

    exports ru.solomka.client.core.component.entity;
    opens ru.solomka.client.core.component.entity to javafx.fxml;

    exports ru.solomka.client.core.component.option;
    opens ru.solomka.client.core.component.option to javafx.fxml;
}