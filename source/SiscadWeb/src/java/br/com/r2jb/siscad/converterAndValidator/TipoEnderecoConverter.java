/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.util.TipoEndereco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author bruno
 */
@FacesConverter(value = "tipoEnderecoConverter")
public class TipoEnderecoConverter extends BaseEnumConverter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null != string) {

            return TipoEndereco.getFromDescricaoAndLocale(string, getLocale());

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null != o && o instanceof TipoEndereco) {

            return ((TipoEndereco) o).getDescricao(getLocale());

        }

        return null;

    }
}
