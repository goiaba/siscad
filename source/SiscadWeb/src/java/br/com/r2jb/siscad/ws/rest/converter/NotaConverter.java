/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.converter;

import br.com.r2jb.siscad.business.entity.Nota;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author bruno
 */

@XmlRootElement(name = "nota")
public class NotaConverter {

    private Nota entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of NotaConverter */
    public NotaConverter() {

        entity = new Nota();

    }

    /**
     * Creates a new instance of NotaConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public NotaConverter(Nota entity, URI uri, int expandLevel, boolean isUriExtendable) {

        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;

    }

    /**
     * Creates a new instance of NotaConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public NotaConverter(Nota entity, URI uri, int expandLevel) {

        this(entity, uri, expandLevel, false);

    }

    /**
     * Getter for idnota.
     *
     * @return value for idnota
     */
    @XmlElement
    public Integer getIdnota() {

        return (expandLevel > 0) ? entity.getId() : null;

    }

    /**
     * Setter for idnota.
     *
     * @param value the value to set
     */
    public void setIdnota(Integer value) {

        entity.setId(value);

    }

    /**
     * Getter for valor.
     *
     * @return value for valor
     */
    @XmlElement
    public Float getValor() {

        return (expandLevel > 0) ? entity.getValor() : null;

    }

    /**
     * Setter for valor.
     *
     * @param value the value to set
     */
    public void setValor(Float value) {

        entity.setValor(value);

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
     * Returns the Nota entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Nota getEntity() {

        if (entity.getId() == null) {

            NotaConverter converter = UriResolver.getInstance().resolve(NotaConverter.class, uri);

            if (converter != null) {

                entity = converter.getEntity();

            }

        }

        return entity;
        
    }

    /**
     * Returns the resolved Nota entity.
     *
     * @return an resolved entity
     */
    public Nota resolveEntity() {

        return entity;

    }

}
