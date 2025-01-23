package com.association.webservice;

import com.association.entity.Groupe;
import com.association.service.GroupeService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/groupes")
public class GroupeWebService {

    private GroupeService groupeService = new GroupeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Groupe> getAllGroupes() {
        return groupeService.getAllGroupes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupeById(@PathParam("id") Long id) {
        Groupe groupe = groupeService.getGroupeById(id);
        if (groupe != null) {
            return Response.ok(groupe).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Groupe introuvable").build();
        }
    }
}
