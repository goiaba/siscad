/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.util.i18n;


import br.com.r2jb.siscad.business.ServiceLocator;
import java.io.IOException;
import java.util.Locale;
import java.util.PropertyResourceBundle;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class I18NResourceBundle extends PropertyResourceBundle {

    private static final String sSlash = System.getProperty("file.separator");
    private static final String i18nResourcePath = new StringBuilder().append("resources").append(sSlash)
                                                                    .append("i18n").append(sSlash)
                                                                    .append("messages_#.properties").toString();
            
    public I18NResourceBundle(String sLang) throws IOException {

        super(ServiceLocator.getContext().getResource(i18nResourcePath.replaceFirst("#", sLang)).getInputStream());

    }
    
    public I18NResourceBundle(Locale locale) throws IOException {
        
        super(ServiceLocator.getContext().getResource(i18nResourcePath.replaceFirst("#", formata(locale))).getInputStream());
        
    }

    private static String formata(Locale locale) {

        String sLocale = locale.getLanguage();
        sLocale = sLocale.substring(0,2) + "_" + sLocale.substring(3,5).toUpperCase();

        return sLocale;

    }

    @Override
    public Object handleGetObject(String key) {

        Object obj = super.handleGetObject(key);
        return obj == null ? key : obj;

    }

}
