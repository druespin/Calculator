package org.openjfx.restapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openjfx.App.db;
import static org.openjfx.App.report;


@Path("/rest")
public class RestAPI {

    List<String> list = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRep() {

        return Response.ok("works").build();
    }

    @GET
    @Path("/latest")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLatest() throws SQLException {

        report.clear();
        list = db.get10LatestEntries();
        StringBuilder output = new StringBuilder();

        list.forEach(entry -> {
            report.appendText(entry + "\n");
            output.append(entry + "\n");
        });

        return Response.ok(output.toString()).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getALl() throws SQLException {

        report.clear();
        list = db.getAllEntries();
        StringBuilder output = new StringBuilder();

        list.forEach(entry -> {
            report.appendText(entry + "\n");
            output.append(entry + "\n");
        });

        return Response.ok(output.toString()).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response submitToDb()  {

        if (!report.getText().isEmpty())
        {
            list = Arrays.asList(report.getText().split("\n"));

                list.forEach(entry -> {
                    try {
                        db.insertToDB(entry);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        }
        return Response.ok("Operations filed to history table").build();
    }
}