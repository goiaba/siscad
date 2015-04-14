/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=java.util.Locale.class)
@FacesValidator("localeValidator")
public class LocaleConverterAndValidator implements Converter, Validator {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        for (Locale locale : getAvailableLocales(fc)) {

            if (locale.toString().equals(string)) {

                return locale;

            }

        }

        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        boolean validLocale = getAvailableLocales(fc).contains((Locale) o);

        return validLocale == true ? ((Locale) o).toString() : null;

    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        if (!getAvailableLocales(fc).contains((Locale) o)) {

            FacesMessage message = new FacesMessage(ResourceBundleUtils.getMessage((Locale) o, "localeInvalido"));

            throw new ValidatorException(message);
            
        }

    }

    private List<Locale> getAvailableLocales(FacesContext fc) {

        List<Locale> lstAvailableLocales = new ArrayList<Locale>();

        Iterator<Locale> availableLocales = fc.getApplication().getSupportedLocales();

        while (availableLocales.hasNext()) {

            lstAvailableLocales.add(availableLocales.next());

        }

        return lstAvailableLocales;

    }

}
