/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class BaseBean implements Serializable {

    public static final String CREATE_MODE_PARAMETER = "c";
    public static final String RETRIEVE_MODE_PARAMETER = "r";
    public static final String UPDATE_MODE_PARAMETER = "u";
    public static final String DELETE_MODE_PARAMETER = "d";

    private Map<String, Locale> availableLocales;

    public BaseBean() {
    
        availableLocales = new HashMap<String, Locale>();
        
        Iterator<Locale> locales = getContext().getApplication().getSupportedLocales();

        while (locales.hasNext()) {

            Locale locale = locales.next();

            String key = locale.getLanguage() + "_" + locale.getCountry();

            availableLocales.put(key, locale);

        }

    }

    public HttpSession getSession() {

        return (HttpSession) getContext().getExternalContext().getSession(true);
    }

    public HttpServletRequest getServletRequest() {

        return ((HttpServletRequest) getContext().getExternalContext().getRequest());
    }

    public final FacesContext getContext() {

        return FacesContext.getCurrentInstance();

    }

    public List<SelectItem> getSupportedLocales() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (String key : availableLocales.keySet()) {

            Locale locale = availableLocales.get(key);

            itens.add(new SelectItem(locale, locale.getDisplayName(getLocale())));

        }

        return itens;

    }

    public final Locale getDefaultLocale() {

        return getContext().getApplication().getDefaultLocale();
        
    }

    public Locale getLocale() {

        if (getContext().getViewRoot() != null && getContext().getViewRoot().getLocale() != null) {

            return getContext().getViewRoot().getLocale();

        }

        return getDefaultLocale();

    }

    public void setLocale(String locale) {

        Locale l = availableLocales.get(locale);

        getContext().getViewRoot().setLocale((null != l) ? l : getDefaultLocale());

    }

    public void addFacesMessage(String message) {

        addFacesMessage(null, message, FacesMessage.SEVERITY_INFO);

    }

    public void addFacesMessage(String elementId, String message) {

        addFacesMessage(null, message, FacesMessage.SEVERITY_INFO);

    }

    public void addFacesMessage(String message, FacesMessage.Severity severity) {

        addFacesMessage(null, message, severity);

    }
    
    public void addFacesMessage(String message, FacesMessage.Severity severity, String... params) {

        addFacesMessage(null, message, severity, params);
        
    }

    public void addFacesMessage(String elementId, String message, FacesMessage.Severity severity) {

        getContext().addMessage(elementId, new FacesMessage(severity, ResourceBundleUtils.getMessage(getLocale(), message), null));

    }
    
    public void addFacesMessage(String elementId, String message, FacesMessage.Severity severity, String... params) {
        
        getContext().addMessage(elementId, new FacesMessage(severity, ResourceBundleUtils.getMessage(getLocale(), message, params), null));

    }
    
}
