/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.BaseEntity;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class BaseConverterValidator<T extends BaseEntity, U extends BaseFacade<T>> implements Serializable, Converter, Validator {

    private Class<U> clazz;

    public BaseConverterValidator() {

        this.clazz = (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String submittedValue) {

        if (StringUtils.isEmpty(submittedValue)) {

            return null;

        }

        try {

            Integer id = Integer.parseInt(submittedValue);

            return ServiceLocator.getFacade(clazz).find(id);

        } catch (NumberFormatException e) {

            Logger.getLogger(clazz.getName()).log(Level.SEVERE, "BCV.getAsObject", e);
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Entity: " + entityClass.getName()));

        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null == o) {

            return null;
            
        }

        return ((T) o).getId() != null ? ((T) o).getId().toString() : null;

    }


    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ((UIInput) uic).setValid(true);

    }

}
