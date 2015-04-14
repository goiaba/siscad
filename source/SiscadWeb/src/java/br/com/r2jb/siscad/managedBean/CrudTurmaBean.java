/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.serie.SerieFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import br.com.r2jb.siscad.business.facade.turno.TurnoFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudTurmaBean extends AbstractCrudBean<Turma> {

    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    private SerieFacade serieFacade = ServiceLocator.getFacade(SerieFacade.class);
    private TurnoFacade turnoFacade = ServiceLocator.getFacade(TurnoFacade.class);
    private TurmaFacade turmaFacade = ServiceLocator.getFacade(TurmaFacade.class);

    private Boolean abrirTurma = Boolean.FALSE;

    @PostConstruct
    public void init() {

        resetBean();

    }

    private void resetBean() {

        setEntity(new Turma());

    }

    @Override
    protected void handleCreate(Turma entity) {

        try {

            if (this.abrirTurma) {

                entity.setStatus(StatusTurma.ABERTA);

            } else {

                entity.setStatus(StatusTurma.CRIADA);

            }

            if (!turmaFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                turmaFacade.inserir(entity);
                resetBean();
                addFacesMessage("cadastroSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleCreate.Exception", e);

        }

    }

    @Override
    protected List<Turma> handleRetrieve(String searchTerm) {

        try {

            if (null != getEntity()) {
            
                return turmaFacade.findByExample(getEntity());

            }

            return Collections.EMPTY_LIST;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleResultList.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Turma entity) {

        try {

            if (!turmaFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                turmaFacade.alterar(entity);
                addFacesMessage("alteradoSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Turma entity) {

        try {

            turmaFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {
    }

    public List<SelectItem> getListaCodigoTurma() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (char c = 65; c <= 90; ++c) {
            
            itens.add(new SelectItem(c));
        
        }

        return itens;
        
    }

    public List<SelectItem> getListaEscola() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        try {

            for (Escola escola : escolaFacade.findAll()) {

                itens.add(new SelectItem(escola, escola.getNome()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleResultList.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaSerie() {

        try {

            if (null != getEntity() && null != getEntity().getEscola()) {

                List<SelectItem> itens = new ArrayList<SelectItem>();

                for (Serie s : serieFacade.findByEscola(getEntity().getEscola())) {

                    itens.add(new SelectItem(s, s.getDescricao()));

                }

                return itens;

            }

            return Collections.EMPTY_LIST;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleResultList.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaTurno() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        try {

            for (Turno t : turnoFacade.findAll()) {

                itens.add(new SelectItem(t, t.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CTB.handleResultList.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaPeriodo() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (Periodo p : Periodo.values()) {

            itens.add(new SelectItem(p, p.getDescricao(getLocale())));

        }

        return itens;

    }

    public List<SelectItem> getListaAno() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (Ano ano : Ano.values()) {

            if (ano.getValor() >= Ano.getAnoAtual().getValor()) {
            
                itens.add(new SelectItem(ano, Integer.toString(ano.getValor())));

            }

        }

        return itens;

    }

    public List<SelectItem> getListaStatusTurma() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (StatusTurma st : StatusTurma.values()) {

            itens.add(new SelectItem(st, st.getDescricao(getLocale())));

        }

        return itens;

    }

    public Boolean getAbrirTurma() {
        return abrirTurma;
    }

    public void setAbrirTurma(Boolean abrirTurma) {
        this.abrirTurma = abrirTurma;
    }

}
