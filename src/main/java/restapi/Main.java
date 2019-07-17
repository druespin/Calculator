package restapi;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.glassfish.jersey.jdkhttp.JdkHttpServerFactory.*;


public class Main extends ResourceConfig {

    public static void main(String[] args) throws Exception {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig(RestAPI.class);

        HttpServer server = createHttpServer(baseUri, config);
        System.out.println("Press Enter to stop the server. ");
        System.in.read();
        server.stop(1);
    }

}
