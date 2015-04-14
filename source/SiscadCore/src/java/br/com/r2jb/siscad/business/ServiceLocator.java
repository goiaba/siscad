/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business;

import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import br.com.r2jb.siscad.util.security.SiscadSecurity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class ServiceLocator {
    
    private static final String MAIN_CONTEXT = "resources/properties-config.xml";

    private static ApplicationContext context = new ClassPathXmlApplicationContext(MAIN_CONTEXT);

    public static ApplicationContext getContext() {

        return context;

    }

    public static <T> T getBean(String sBeanName, Class clazz) {

        return (T) getContext().getBean(sBeanName, clazz);

    }

    public static <T extends BaseDAO> T getDAO(Class<T> clazz) {

        return (T) getContext().getBean(clazz.getSimpleName());

    }

    public static <T extends BaseFacade> T getFacade(Class<T> clazz) {

        return (T) getContext().getBean(clazz.getSimpleName());

    }

    public static SiscadSecurity security() {

        return getContext().getBean(SiscadSecurity.class);

    }

}
