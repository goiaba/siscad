/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.appconfig;

import br.com.r2jb.siscad.business.ServiceLocator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class ApplicationConfig {

    private static final String sSlash = System.getProperty("file.separator");
    private static final String resourcesPath = new StringBuilder().append("resources").append(sSlash)
                                                                 .append("application.properties").toString();

    private Properties properties;
    private static ApplicationConfig applicationConfig;

    protected ApplicationConfig() {

        try {

            InputStream is = ServiceLocator.getContext().getResource(resourcesPath).getInputStream();

            properties = new Properties();
            properties.load(is);

        } catch (IOException ex) {

            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public static ApplicationConfig getInstance() {

        if (applicationConfig == null) {

            applicationConfig = new ApplicationConfig();

        }

        return applicationConfig;

    }

    public String getProperty(String sKey) {

        return properties.getProperty(sKey);

    }

    public Integer getIntegerProperty(String sKey) {

        String sValue = properties.getProperty(sKey);

        if (null != sValue) {

            return Integer.parseInt(sValue);

        }

        return null;

    }

}
