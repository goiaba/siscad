/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.service;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.business.facade.nota.NotaFacade;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import br.com.r2jb.siscad.ws.rest.converter.NotaConverter;
import com.sun.jersey.spi.resource.Singleton;
import com.sun.jersey.api.spring.Autowire;
import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author bruno
 */

@Path("/notas/")
@Singleton
@Autowire
public class NotasResource {

    @Context protected ResourceContext resourceContext;
    @Context protected UriInfo uriInfo;
  
    /** Creates a new instance of NotasResource */
    public NotasResource() {
    }

    @GET
    @Produces({"application/xml"})
    @Path("notaByDMA")
    public NotaConverter getNotaByDisciplinaAvaliacaoEMatricula(@QueryParam("idDisciplina") String idDisciplina,
                                                                @QueryParam("idAvaliacao") String idAvaliacao,
                                                                @QueryParam("idMatricula") String idMatricula,
                                                                @QueryParam("expandLevel") @DefaultValue("1") int expandLevel) {

        Nota nota = ServiceLocator.getFacade(NotaFacade.class).findByDisciplinaAvaliacaoEMatricula(idDisciplina, idAvaliacao, idMatricula);

        if (null == nota) {

            throw new WebApplicationException(new Throwable("Nota não existe."), 404);

        }

        return new NotaConverter(nota, uriInfo.getAbsolutePath(), expandLevel);

    }

    /**
     * Returns a dynamic instance of NotaResource used for entity navigation.
     *
     * @return an instance of NotaResource
     */
    @Path("{idnota}/")
    public NotaResource getNotaResource(@PathParam("idnota") Integer id) {

        NotaResource notaResource = resourceContext.getResource(NotaResource.class);

        notaResource.setId(id);

        return notaResource;

    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Nota instances
     */
    protected Collection<Nota> getEntities(int start, int max) {

        return ServiceLocator.getFacade(NotaFacade.class).findAll(start, max);

    }

}
