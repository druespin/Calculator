package restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


    @Path("/api")
    public class RestAPI {


        @GET
        public Response getMsg() {

            String output = "Jersey say";

            return Response.status(200).entity(output).build();

        }
    }
