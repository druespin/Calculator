package org.openjfx.restapi;


import com.sun.net.httpserver.HttpServer;
import javafx.concurrent.Task;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.glassfish.jersey.jdkhttp.JdkHttpServerFactory.createHttpServer;


public class ServerSetup extends Task {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8089).build();
        ResourceConfig config = new ResourceConfig(RestAPI.class);

        @Override
        protected Object call () throws Exception {
            return createHttpServer(baseUri, config);
        }
}

