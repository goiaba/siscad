/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.util.SituacaoMatricula;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(value="situacaoMatriculaConverter")
public class SituacaoMatriculaConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return SituacaoMatricula.getFromDescricaoAndLocale(string, getLocale());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o.getClass().equals(SituacaoMatricula.class)) {

            return ((SituacaoMatricula) o).getDescricao(getLocale());

        }

        return null;

    }

}
