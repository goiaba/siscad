/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.service;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import br.com.r2jb.siscad.ws.rest.converter.CursoConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author Joaquim
 */

public class CursoResource {

    @Context protected ResourceContext resourceContext;
    @Context protected UriInfo uriInfo;

    protected Integer id;
  
    /** Creates a new instance of CursoResource */
    public CursoResource() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get method for retrieving an instance of Curso identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of CursoConverter
     */
    @GET
    @Produces({"application/xml"})
    public CursoConverter get(@QueryParam("expandLevel") @DefaultValue("1") int expandLevel) {

        return new CursoConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);

    }

    /**
     * Put method for updating an instance of Curso identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an CursoConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({"application/xml"})
    @Produces("text/plain")
    public String put(CursoConverter data) {

        try {
        
            ServiceLocator.getFacade(CursoFacade.class).alterar(data.resolveEntity());

            return "Alteração efetuada com sucesso.";

        } catch (Exception e) {

            return "Falha na alteração.";

        }

    }

    /**
     * Delete method for deleting an instance of Curso identified by id.
     *
     * @param id identifier for the entity
     */
    @DELETE
    @Produces("text/plain")
    public String delete() {

        try {
        
            ServiceLocator.getFacade(CursoFacade.class).remover(getEntity());

            return "Remoção efetuadao com sucesso.";

        } catch (Exception e) {

            return "Falha na remoção.";

        }


    }

    /**
     * Returns an instance of Curso identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Curso
     */
    protected Curso getEntity() {

        try {

            Curso curso = ServiceLocator.getFacade(CursoFacade.class).find(id);

            if (null == curso) {

                throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);

            }

            return curso;

        } catch (Exception ex) {

            throw new WebApplicationException(new Throwable(ResourceBundleUtils.getMessage("erroInesperado")), 404);

        }

    }

}
