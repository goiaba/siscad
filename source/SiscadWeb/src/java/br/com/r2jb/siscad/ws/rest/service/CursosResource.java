/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.service;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import br.com.r2jb.siscad.ws.rest.converter.CursosConverter;
import br.com.r2jb.siscad.ws.rest.converter.CursoConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author Joaquim
 */

@Path("/cursos/")
public class CursosResource {

    @Context protected ResourceContext resourceContext;
    @Context protected UriInfo uriInfo;
  
    /** Creates a new instance of CursoWSsResource */
    public CursosResource() {
    }

    /**
     * Get method for retrieving a collection of Curso instance in XML format.
     *
     * @return an instance of CursosConverter
     */
    @GET
    @Produces({"application/xml"})
    public CursosConverter get(@QueryParam("start") @DefaultValue("0") int start,
                               @QueryParam("max") @DefaultValue("10") int max,
                               @QueryParam("expandLevel") @DefaultValue("1") int expandLevel) {

        return new CursosConverter(getEntities(start, max), uriInfo.getAbsolutePath(), expandLevel);
        
    }

    /**
     * Post method for creating an instance of Curso using XML as the input format.
     *
     * @param data an CursoConverter entity that is deserialized from an XML stream
     * @return an instance of CursoConverter
     */
    @POST
    @Consumes({"application/xml"})
    public Response post(CursoConverter data) {

        Curso entity = data.resolveEntity();

        ServiceLocator.getFacade(CursoFacade.class).inserir(entity);
        
        return Response.created(uriInfo.getAbsolutePath().resolve(entity.getId() + "/")).build();

    }

    /**
     * Returns a dynamic instance of CursoResource used for entity navigation.
     *
     * @return an instance of CursoResource
     */
    @Path("{idcurso}/")
    public CursoResource getCursoWSResource(@PathParam("idcurso") Integer id) {

        CursoResource cursoResource = resourceContext.getResource(CursoResource.class);

        cursoResource.setId(id);

        return cursoResource;

    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Curso instances
     */
    protected Collection<Curso> getEntities(int start, int max) {

        return ServiceLocator.getFacade(CursoFacade.class).findAll(start, max);

    }

}
