/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.managedBean.AbstractCrudBean.Mode;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter("modeConverter")
public class ModeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return Mode.valueOf(string.toUpperCase());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o instanceof Mode) {

            return ((Mode) o).toString();

        }

        return null;

    }

}
