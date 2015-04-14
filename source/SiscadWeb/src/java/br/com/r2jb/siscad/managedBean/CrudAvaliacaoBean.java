/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException.Relacionamento;
import br.com.r2jb.siscad.business.facade.avaliacao.AvaliacaoFacade;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudAvaliacaoBean extends AbstractCrudBean<Avaliacao> {

    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    private PeriodoAvaliacaoFacade periodoAvaliacaoFacade = ServiceLocator.getFacade(PeriodoAvaliacaoFacade.class);
    private AvaliacaoFacade avaliacaoFacade = ServiceLocator.getFacade(AvaliacaoFacade.class);

    private Escola escolaSelecionada;
    private Curso cursoSelecionado;
    private Periodo periodoSelecionado;
    private Ano anoSelecionado;

    private Boolean filtroPeriodoAvaliacao = Boolean.FALSE;

    @PostConstruct
    public void init() {

        resetBean();

    }

    private void resetBean() {

        setEntity(new Avaliacao());
        escolaSelecionada = null;
        cursoSelecionado = null;
        periodoSelecionado = null;
        anoSelecionado = null;
    }

    @Override
    protected void handleCreate(Avaliacao entity) {

        try {

            if (!avaliacaoFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                avaliacaoFacade.inserir(entity);
                resetBean();
                addFacesMessage("cadastroSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleCreate.Exception", e);

        }

    }

    @Override
    protected List<Avaliacao> handleRetrieve(String searchTerm) {

        try {

            return avaliacaoFacade.findByExample(getEntity());

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Avaliacao entity) {

        try {

            if (!avaliacaoFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                avaliacaoFacade.alterar(entity);
                addFacesMessage("alteradoSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Avaliacao entity) {

        try {

            avaliacaoFacade.remover(entity);
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaEscola.Exception", e);
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaCurso.Exception", e);
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

    public List<SelectItemGroup> getListaPeriodoAvaliacao() {

        try {

            if (getCreateMode()) {

                if (null == cursoSelecionado || null == periodoSelecionado || null == anoSelecionado) {

                    return Collections.EMPTY_LIST;

                }

                List<SelectItemGroup> listaPerAval = new ArrayList<SelectItemGroup>();

                SelectItemGroup grupo = new SelectItemGroup();

                List<SelectItem> itens = new ArrayList<SelectItem>();

                for (PeriodoAvaliacao pa : periodoAvaliacaoFacade.findByCursoPeriodoEAno(cursoSelecionado, periodoSelecionado, anoSelecionado)) {

                    itens.add(new SelectItem(pa, pa.getDescricao()));

                }

                grupo.setNoSelectionOption(true);
                SelectItem[] itensArray = new SelectItem[itens.size()];
                grupo.setSelectItems(itens.toArray(itensArray));

                listaPerAval.add(grupo);

                return listaPerAval;

            } else {

                if (null == cursoSelecionado) {

                    return Collections.EMPTY_LIST;

                }

                return criaListaDeItensPorPeriodoEAno(periodoAvaliacaoFacade.findByCurso(cursoSelecionado, filtroPeriodoAvaliacao));

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaPeriodoAvaliacao.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    private List<SelectItemGroup> criaListaDeItensPorPeriodoEAno(List<PeriodoAvaliacao> listaPeriodoAvaliacao) {

        List<SelectItemGroup> listaPerAval = new ArrayList<SelectItemGroup>();

        Map<String, List<PeriodoAvaliacao>> periodoAvaliacaoPorPeriodoEAno = separaPeriodoAvaliacaoPorPeriodoEAno(listaPeriodoAvaliacao);

        for (String descricaoGrupo : periodoAvaliacaoPorPeriodoEAno.keySet()) {

            SelectItemGroup grupo = new SelectItemGroup(descricaoGrupo);

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (PeriodoAvaliacao perAval : periodoAvaliacaoPorPeriodoEAno.get(descricaoGrupo)) {

                itens.add(new SelectItem(perAval, perAval.getDescricao()));

            }

            grupo.setNoSelectionOption(true);
            SelectItem[] itensArray = new SelectItem[itens.size()];
            grupo.setSelectItems(itens.toArray(itensArray));

            listaPerAval.add(grupo);

        }

        return listaPerAval;

    }

    private Map<String, List<PeriodoAvaliacao>> separaPeriodoAvaliacaoPorPeriodoEAno(List<PeriodoAvaliacao> listaPeriodoAvaliacao) {

        Map<String, List<PeriodoAvaliacao>> result = new HashMap<String, List<PeriodoAvaliacao>>();

        for (PeriodoAvaliacao periodoAvaliacao : listaPeriodoAvaliacao) {

            String chave = periodoAvaliacao.getSemestre().getDescricao(getLocale()) + " - " + periodoAvaliacao.getAno().getValor();

            List<PeriodoAvaliacao> lista = result.get(chave);

            if (null == lista) {

                lista = new ArrayList<PeriodoAvaliacao>();
            }

            lista.add(periodoAvaliacao);
            result.put(chave, lista);

            Collections.sort(lista);

        }

        return result;

    }

    public Escola getEscolaSelecionada() {
        return escolaSelecionada;
    }

    public void setEscolaSelecionada(Escola escolaSelecionada) {
        this.escolaSelecionada = escolaSelecionada;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Periodo getPeriodoSelecionado() {
        return periodoSelecionado;
    }

    public void setPeriodoSelecionado(Periodo periodoSelecionado) {
        this.periodoSelecionado = periodoSelecionado;
    }

    public Ano getAnoSelecionado() {
        return anoSelecionado;
    }

    public void setAnoSelecionado(Ano anoSelecionado) {
        this.anoSelecionado = anoSelecionado;
    }

    public Boolean getFiltroPeriodoAvaliacao() {
        return filtroPeriodoAvaliacao;
    }

    public void setFiltroPeriodoAvaliacao(Boolean filtroPeriodoAvaliacao) {
        this.filtroPeriodoAvaliacao = filtroPeriodoAvaliacao;
    }

}
