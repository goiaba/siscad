/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.ws.rest.converter;

import br.com.r2jb.siscad.business.entity.Curso;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 *
 * @author Joaquim
 */

@XmlRootElement(name = "cursos")
public class CursosConverter {

    private Collection<Curso> entities;
    private Collection<CursoConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CursosConverter */
    public CursosConverter() {
    }

    /**
     * Creates a new instance of CursosConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CursosConverter(Collection<Curso> entities, URI uri, int expandLevel) {

        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCurso();

    }

    /**
     * Returns a collection of CursoConverter.
     *
     * @return a collection of CursoConverter
     */
    @XmlElement
    public final Collection<CursoConverter> getCurso() {

        if (items == null) {

            items = new ArrayList<CursoConverter>();

        }

        items.clear();

        for (Curso entity : entities) {

                items.add(new CursoConverter(entity, uri, expandLevel, true));

        }

        return items;
        
    }

    /**
     * Sets a collection of CursoConverter.
     *
     * @param a collection of CursoConverter to set
     */
    public void setCurso(Collection<CursoConverter> items) {

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
     * Returns a collection Curso entities.
     *
     * @return a collection of Curso entities
     */
    @XmlTransient
    public Collection<Curso> getEntities() {

        entities = new ArrayList<Curso>();

        if (items != null) {

            for (CursoConverter item : items) {

                entities.add(item.getEntity());

            }

        }

        return entities;

    }
    
}
