/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(value="tipoPerfilConverter")
public class TipoPerfilConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return TipoPerfil.getFromDescricaoAndLocale(string, getLocale());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o.getClass() == TipoPerfil.class) {
            
            return ((TipoPerfil) o).getDescricao(getLocale());
            
        }
        
        return null;
        
    }

}
