module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.ws.rs;
    requires jdk.httpserver;
    requires jersey.server;
    requires jersey.container.jdk.http;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}