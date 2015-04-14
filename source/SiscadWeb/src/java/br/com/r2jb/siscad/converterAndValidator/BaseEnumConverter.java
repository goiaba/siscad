/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class BaseEnumConverter implements Converter, Serializable {

    protected Locale getLocale() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (null != context && null != context.getViewRoot()) {

            return context.getViewRoot().getLocale();

        } else {

            return ResourceBundleUtils.DEFAULT_LOCALE;

        }

    }

}
