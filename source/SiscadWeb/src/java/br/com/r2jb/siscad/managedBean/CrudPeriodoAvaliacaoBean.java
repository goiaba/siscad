/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException.Relacionamento;
import br.com.r2jb.siscad.business.exception.UniqueKeyConstraintViolatedException;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
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
public class CrudPeriodoAvaliacaoBean extends AbstractCrudBean<PeriodoAvaliacao> {

    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    private PeriodoAvaliacaoFacade periodoAvaliacaoFacade = ServiceLocator.getFacade(PeriodoAvaliacaoFacade.class);

    private Escola escolaSelecionada;

    @PostConstruct
    public void init() {

        resetBean();

    }

    private void resetBean() {

        setEntity(new PeriodoAvaliacao());

    }
   
    @Override
    protected void handleCreate(PeriodoAvaliacao entity) {

        try {

            periodoAvaliacaoFacade.inserir(entity);
            resetBean();
            addFacesMessage("cadastroSucesso");

        } catch (UniqueKeyConstraintViolatedException e) {

            addFacesMessage("erroConstraint");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPAB.handleCreate.Exception", e);

        }

    }

    @Override
    protected List<PeriodoAvaliacao> handleRetrieve(String searchTerm) {

        try {

            return periodoAvaliacaoFacade.findByExample(getEntity());

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPAB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(PeriodoAvaliacao entity) {

        try {

            periodoAvaliacaoFacade.alterar(entity);
            addFacesMessage("alteradoSucesso");

        } catch (UniqueKeyConstraintViolatedException e) {

            addFacesMessage("erroConstraint");
            
        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPAB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(PeriodoAvaliacao entity) {

        try {

            periodoAvaliacaoFacade.remover(entity);
            addFacesMessage("remocaoSucesso");
            return true;

        } catch (EntidadePossuiRelacionamentoException e) {
            
            for (Relacionamento r : e.getRelacionamentosPreenchidos()) {
                
                addFacesMessage(r.getDescricao());
                
            }

            return false;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPAB.getListaEscola.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaCurso() {

        if (null == escolaSelecionada) {

            return Collections.EMPTY_LIST;

        }

        List<SelectItem> itens = new ArrayList<SelectItem>();

        try {

            for (Curso curso : cursoFacade.findByEscola(escolaSelecionada)) {

                itens.add(new SelectItem(curso, curso.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPAB.getListaCurso.Exception", e);
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

    public Escola getEscolaSelecionada() {
        return escolaSelecionada;
    }

    public void setEscolaSelecionada(Escola escolaSelecionada) {
        this.escolaSelecionada = escolaSelecionada;
    }
}
