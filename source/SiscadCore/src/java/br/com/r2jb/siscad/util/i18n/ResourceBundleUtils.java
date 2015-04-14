/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.util.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class ResourceBundleUtils implements Serializable {

    private static final String DEFAULT_MESSAGE_BUNDLE_CLASS = "br.com.r2jb.siscad.util.i18n.I18NResourceBundle";
    public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");

    /**
     *
     * @param sKey
     * @return a mensagem internacionalizada relacionada a @param sKey no locale
     *  padrão.
     */
    public static String getMessage(String sKey) {

        if (null == sKey) {

            return "[null key]";

        }

        return ResourceBundle.getBundle(DEFAULT_MESSAGE_BUNDLE_CLASS, DEFAULT_LOCALE).getString(sKey);

    }

    public static String getMessage(Locale locale, String sKey) {

        if (null == sKey) {

            return "[null key]";

        }

        if (null != locale) {

            return ResourceBundle.getBundle(DEFAULT_MESSAGE_BUNDLE_CLASS, locale).getString(sKey);

        } else {

            return getMessage(sKey);

        }

    }

    /**
     * 
     * @param locale
     * @param sKey
     * @param params
     * @return a mensagem internacionalizada relacionada a @param sKey e seta
     *  os parâmetros passados pelo usuário, na ordem.
     */
    public static String getMessage(Locale locale, String sKey, String... params) {

        String sMessage = getMessage(locale, sKey);
        Integer index = 0;

        for (String param : params) {

            sMessage = sMessage.replace("{" + index + "}", param);
            index++;

        }

        return sMessage;

    }
    
}
