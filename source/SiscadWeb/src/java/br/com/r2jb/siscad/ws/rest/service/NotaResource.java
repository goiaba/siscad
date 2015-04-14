/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.service;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.business.facade.nota.NotaFacade;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import br.com.r2jb.siscad.ws.rest.converter.NotaConverter;
import com.sun.jersey.api.spring.Autowire;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author bruno
 */

@Autowire
public class NotaResource {

    @Context protected ResourceContext resourceContext;
    @Context protected UriInfo uriInfo;

    protected Integer id;
  
    /** Creates a new instance of NotaResource */
    public NotaResource() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get method for retrieving an instance of Nota identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of NotaConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public NotaConverter get(@QueryParam("expandLevel") @DefaultValue("1") int expandLevel) {

        return new NotaConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);

    }

    /**
     * Returns an instance of Nota identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Nota
     */
    protected Nota getEntity() {

        try {

            Nota nota = ServiceLocator.getFacade(NotaFacade.class).find(id);

            if (null == nota) {

                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);

            }

            return nota;

        } catch (Exception ex) {

            throw new WebApplicationException(new Throwable(ResourceBundleUtils.getMessage("erroInesperado")), 404);

        }

    }

}
