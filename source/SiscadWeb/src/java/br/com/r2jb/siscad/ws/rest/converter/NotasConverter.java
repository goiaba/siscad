/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.converter;

import br.com.r2jb.siscad.business.entity.Nota;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */

@XmlRootElement(name = "notas")
public class NotasConverter {

    private Collection<Nota> entities;
    private Collection<NotaConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of NotasConverter */
    public NotasConverter() {
    }

    /**
     * Creates a new instance of NotasConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public NotasConverter(Collection<Nota> entities, URI uri, int expandLevel) {

        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getNota();

    }

    /**
     * Returns a collection of NotaConverter.
     *
     * @return a collection of NotaConverter
     */
    @XmlElement
    public final Collection<NotaConverter> getNota() {

        if (items == null) {

            items = new ArrayList<NotaConverter>();

        }

        items.clear();

        for (Nota entity : entities) {

            items.add(new NotaConverter(entity, uri, expandLevel, true));

        }

        return items;

    }

    /**
     * Sets a collection of NotaConverter.
     *
     * @param a collection of NotaConverter to set
     */
    public void setNota(Collection<NotaConverter> items) {

        this.items = items;

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
     * Returns a collection Nota entities.
     *
     * @return a collection of Nota entities
     */
    @XmlTransient
    public Collection<Nota> getEntities() {

        entities = new ArrayList<Nota>();

        if (items != null) {

            for (NotaConverter item : items) {

                entities.add(item.getEntity());

            }

        }

        return entities;

    }
    
}
