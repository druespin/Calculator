package org.openjfx.restapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.openjfx.App.report;


@Path("/rest")
public class RestAPI {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMsg() {

        return report.getText();
    }


    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response submit() {

        report.getText();

        return Response.ok().build();

    }
}