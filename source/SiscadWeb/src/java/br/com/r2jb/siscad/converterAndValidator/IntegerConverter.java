/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter("integerConverter")
public class IntegerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (null == string) {
            
            return null;
        }
        
        if ("".equals(string)) {
            
            return 0;
            
        }
        
        return Integer.parseInt(string);
    
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (null == o || !(o instanceof Integer)) {
            
            return null;
        
        }
        
        if (((Integer) o).equals(0)) {
            
            return "";
            
        } 
        
        return ((Integer) o).toString();
        
    }
    
}
