/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.util.TipoTelefone;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author bruno
 */
@FacesConverter(value = "tipoTelefoneConverter")
public class TipoTelefoneConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return TipoTelefone.getFromDescricaoAndLocale(string, getLocale());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o instanceof TipoTelefone) {
            
            return ((TipoTelefone) o).getDescricao(getLocale());
            
        }
        
        return null;
        
    }
    
}
