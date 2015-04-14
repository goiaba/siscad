/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.util.Periodo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter("periodoConverter")
public class PeriodoConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Não é para ser utilizado.");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o instanceof Periodo) {

            return ((Periodo) o).getDescricao(getLocale());

        }

        return null;

    }

}
