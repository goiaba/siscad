/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.util.Sexo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter("sexoConverter")
public class SexoConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return Sexo.getFromDescricaoAndLocale(string, getLocale());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o instanceof Sexo) {

            return ((Sexo) o).getDescricao(getLocale());

        }

        return null;

    }

}
