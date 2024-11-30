package org.pavila.webapp.jaxws.controllers;

import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.pavila.webapp.jaxws.models.Curso;
import org.pavila.webapp.jaxws.services.CursoService;

@RequestScoped
@Path("/cursos")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CursoRestController {

    @Inject
    private CursoService service;

    @GET
    public Response listar() {
        return Response.ok(service.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        var cursoOptional = service.porId(id);
        // if (cursoOptional.isPresent())
        // return Response.ok(cursoOptional.get()).build();

        // return Response.status(Response.Status.NOT_FOUND).build();

        return cursoOptional.map(curso -> Response.ok(curso).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response crear(Curso curso) {
        try {

            Curso nuevCurso = service.guardar(curso);
            return Response.ok(nuevCurso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response editar(@PathParam("id") Long id, Curso curso) {

        Optional<Curso> cursoOptional = service.porId(id);
        if (!cursoOptional.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        Curso nuevoCurso = cursoOptional.get();
        nuevoCurso.setNombre(curso.getNombre());
        nuevoCurso.setDescripcion(curso.getDescripcion());
        nuevoCurso.setDuracion(curso.getDuracion());
        nuevoCurso.setInstructor(curso.getInstructor());

        try {
            service.guardar(nuevoCurso);
            return Response.ok(nuevoCurso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Optional<Curso> cursoOptional = service.porId(id);
        if (!cursoOptional.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        try {

            service.eliminar(cursoOptional.get().getId());
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

    }
}
