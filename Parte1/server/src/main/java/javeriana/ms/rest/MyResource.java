package javeriana.ms.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javeriana.ms.rest.BD.BD;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api/paseo")
public class MyResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvailableTrips() throws SQLException {
        BD db = new BD();
        return Response.ok(db.getAvailableTrips()).build();
    }
    @Path("createPaseo/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrip(Trip trip) {
        try {
            BD bd=new BD();
            int code = bd.createTrip(trip);
            if (code == 201) {
                return Response.created(null).build();
            }
            return Response.notModified().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }

    @Path("actualizarPaseo/{id}")
    @PUT
    public Response update(@PathParam("id") Integer id, @QueryParam("origen") String origen,
                           @QueryParam("destino") String destino) {
        try {
            BD bd=new BD();
            int code = bd.update(id, origen, destino);
            if (code == 200) {
                return Response.ok().build();
            }
            return Response.notModified().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }


    @Path("deletepaseo/{id}")
    @DELETE
    public Response deleteTrip(@PathParam("id") Integer id) {
        try {
            BD bd=new BD();
            int code = bd.deleteTrip(id);
            if (code == 200) {
                return Response.ok("Resource deleted").build();
            } else if (code == 404) {
                return Response.noContent().build();
            }
            return Response.serverError().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }


}
