/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class ListaEscolaBean extends BaseBean {

    private List<Escola> listaEscolasCadastradas;
    transient private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);

    /** Creates a new instance of ListaEscolaBean */
    public ListaEscolaBean() {
    }

    @PostConstruct
    public void init() {
        this.listaEscolasCadastradas = escolaFacade.findAll();
    }

    /**
     * @return the listaEscolasCadastradas
     */
    public List<Escola> getListaEscolasCadastradas() {
        return listaEscolasCadastradas;
    }

    /**
     * @param listaEscolasCadastradas the listaEscolasCadastradas to set
     */
    public void setListaEscolasCadastradas(List<Escola> listaEscolasCadastradas) {
        this.listaEscolasCadastradas = listaEscolasCadastradas;
    }

    /**
     * @return the escolaFacade
     */
    public EscolaFacade getEscolaFacade() {
        return escolaFacade;
    }

    /**
     * @param escolaFacade the escolaFacade to set
     */
    public void setEscolaFacade(EscolaFacade escolaFacade) {
        this.escolaFacade = escolaFacade;
    }
}
