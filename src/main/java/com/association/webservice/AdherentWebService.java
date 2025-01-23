package com.association.webservice;

import com.association.entity.Adherent;
import com.association.service.AdherentService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/adherents")
public class AdherentWebService {

    private AdherentService adherentService = new AdherentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adherent> getAllAdherents() {
        return adherentService.getAllAdherents();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdherentById(@PathParam("id") Long id) {
        Adherent adherent = adherentService.getAdherentById(id);
        if (adherent != null) {
            return Response.ok(adherent).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Adhérent introuvable").build();
        }
    }
}
