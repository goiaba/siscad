/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.phaseListener;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.managedBean.LoginBean;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class SiscadWebPhaseListener implements PhaseListener {

    private static final String fs = System.getProperty("file.separator");
    
    private static final String LOGIN_PAGE = fs + "pages" + fs + "login" + fs + "login.jsf";
    private static final String UNAUTHORIZED_ACCESS_PAGE = fs + "acessoNegado.jsf";

    @Override
    public void afterPhase(PhaseEvent pe) {

        try {

            FacesContext fc = pe.getFacesContext();
            ExternalContext ec = fc.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();

            String sRequestedPage = fc.getViewRoot().getViewId();

            if (!ServiceLocator.security().isUnsafeResource(sRequestedPage)) {

                boolean isAccessAllowed = false;
                Usuario user = (Usuario) request.getSession().getAttribute(LoginBean.SESSION_USER_KEY);

                if (null != user) {

                    isAccessAllowed = ServiceLocator.security().isAuthorizedAccess(user, sRequestedPage);
                    
                } else {
                
                    ec.redirect(ec.getRequestContextPath() + LOGIN_PAGE);

                }

                if (!isAccessAllowed) {

                    //Logar tentativa de acesso não autorizada.
                    ec.redirect(ec.getRequestContextPath() + UNAUTHORIZED_ACCESS_PAGE);

                }

            }

        } catch (Exception e) {

            throw new FacesException(e);

        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {

        return PhaseId.RESTORE_VIEW;

    }

}
