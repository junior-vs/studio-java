package org.acme.rest;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.persistence.model.Restaurante;
import org.acme.rest.request.RestauranteRequest;
import org.acme.service.RestauranteService;

import java.net.URI;
import java.util.List;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    final RestauranteService restauranteService;

    public RestauranteResource(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

   /* @GET
    public Uni<List<Restaurante>> getAll() {
        return restauranteService.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Uni<Restaurante> getById(@PathParam("id") Long id) {
        return Restaurante.findById(id);
    }

    @POST
    public Uni<Response> create(RestauranteRequest restaurante) {
        return restaurante.persist()
                .map(inserted -> Response.created(URI.create("/restaurantes/" + inserted.id)).entity(inserted).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, @Valid Restaurante updatedRestaurante) {
        return Restaurante.findById(id)
                .map(Restaurante.class::cast)
                .onItem().ifNotNull().transformToUni(restaurante -> {
                    restaurante.atualiza(updatedRestaurante);
                    return restaurante.persist();
                })
                .onItem().ifNotNull().transform(restaurante -> Response.ok(restaurante).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return Restaurante.deleteById(id)
                .map(deleted -> deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build());
    }*/
}