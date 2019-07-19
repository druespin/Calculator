module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.ws.rs;
    requires jdk.httpserver;
    requires jersey.server;
    requires jersey.container.jdk.http;
    requires java.sql;

    opens org.openjfx to javafx.fxml;
    opens org.openjfx.restapi;
    exports org.openjfx.database;
    exports org.openjfx;
}