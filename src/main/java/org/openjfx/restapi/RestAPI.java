package org.openjfx.restapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openjfx.fx.Constants.*;
import static org.openjfx.App.history_window;

/**
 * REST requests
 */

@Path("/rest")
public class RestAPI {

    private List<String> list = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOK() {

        return Response.ok("works").build();
    }

    @GET
    @Path("/latest-10")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLatest() throws SQLException {

        list = db.get10LatestEntries();
        StringBuilder output = new StringBuilder();

        list.forEach(entry -> {
            output.append(entry + "\n");
        });

        return Response.ok(output.toString()).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() throws SQLException {

        list = db.getAllEntries();
        StringBuilder output = new StringBuilder();

        list.forEach(entry -> {
            output.append(entry + "\n");
        });

        return Response.ok(output.toString()).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response submitToDb()  {

        if (!history_window.getText().isEmpty())
        {
            list = Arrays.asList(history_window.getText().split("\n"));

                list.forEach(entry -> {
                    try
                    {
                        db.insertToDB(entry);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        }
        return Response.ok("Operations filed to history table").build();
    }

}