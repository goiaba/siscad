/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class UniqueKeyConstraintViolatedException extends Exception {

    private List<String> entityUniqueKeyAttributes;

    public UniqueKeyConstraintViolatedException() {

        super();

    }

    public UniqueKeyConstraintViolatedException(List<String> entityUniqueKeyAttributes) {

        super();
        this.entityUniqueKeyAttributes = entityUniqueKeyAttributes;

    }

    public UniqueKeyConstraintViolatedException(String message) {

        super(message);

    }

    public UniqueKeyConstraintViolatedException(String message, List<String> entityUniqueKeyAttributes) {

        super(message);
        this.entityUniqueKeyAttributes = entityUniqueKeyAttributes;

    }

    public UniqueKeyConstraintViolatedException(String message, Throwable t) {

        super(message, t);

    }

    public UniqueKeyConstraintViolatedException(String message, Throwable t, List<String> entityUniqueKeyAttributes) {

        super(message, t);
        this.entityUniqueKeyAttributes = entityUniqueKeyAttributes;

    }

    /**
     * @return the entityUniqueKeyAttributes
     */
    public List<String> getEntityUniqueKeyAttributes() {
        return entityUniqueKeyAttributes;
    }

    /**
     * @param entityUniqueKeyAttributes the entityUniqueKeyAttributes to set
     */
    public void setEntityUniqueKeyAttributes(List<String> entityUniqueKeyAttributes) {
        this.entityUniqueKeyAttributes = entityUniqueKeyAttributes;
    }

    public void addAttribute(String attribute) {

        if (null == this.entityUniqueKeyAttributes) {

            this.entityUniqueKeyAttributes = new ArrayList<String>();

        }

        this.entityUniqueKeyAttributes.add(attribute);

    }

}
