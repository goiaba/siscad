/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.converter;

import br.com.r2jb.siscad.business.entity.Curso;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author Joaquim
 */

@XmlRootElement(name = "curso")
public class CursoConverter {

    private Curso entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CursoConverter */
    public CursoConverter() {

        entity = new Curso();

    }

    /**
     * Creates a new instance of CursoConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     * @param isUriExtendable indicates whether the uri can be extended
     */
    public CursoConverter(Curso entity, URI uri, int expandLevel, boolean isUriExtendable) {

        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;

    }

    /**
     * Creates a new instance of CursoConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CursoConverter(Curso entity, URI uri, int expandLevel) {

        this(entity, uri, expandLevel, false);

    }

    /**
     * Getter for idcurso.
     *
     * @return value for idcurso
     */
    @XmlElement
    public Integer getIdcurso() {

        return (expandLevel > 0) ? entity.getId() : null;

    }

    /**
     * Setter for idcurso.
     *
     * @param value the value to set
     */
    public void setIdcurso(Integer value) {

        entity.setId(value);

    }

    /**
     * Getter for descricao.
     *
     * @return value for descricao
     */
    @XmlElement
    public String getDescricao() {

        return (expandLevel > 0) ? entity.getDescricao() : null;

    }

    /**
     * Setter for descricao.
     *
     * @param value the value to set
     */
    public void setDescricao(String value) {

        entity.setDescricao(value);

    }

    /**
     * Getter for ementa.
     *
     * @return value for ementa
     */
    @XmlElement
    public String getEmenta() {

        return (expandLevel > 0) ? entity.getEmenta() : null;

    }

    /**
     * Setter for ementa.
     *
     * @param value the value to set
     */
    public void setEmenta(String value) {

        entity.setEmenta(value);

    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {

        return uri;

    }

    /**
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {

        this.uri = uri;

    }

    /**
     * Returns the Curso entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Curso getEntity() {

        if (entity.getId() == null) {

            CursoConverter converter = UriResolver.getInstance().resolve(CursoConverter.class, uri);

            if (converter != null) {

                entity = converter.getEntity();

            }

        }

        return entity;

    }

    /**
     * Returns the resolved Curso entity.
     *
     * @return an resolved entity
     */
    public Curso resolveEntity() {

        return entity;

    }
    
}
