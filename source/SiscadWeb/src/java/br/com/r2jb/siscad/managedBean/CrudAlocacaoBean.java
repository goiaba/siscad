/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.alocacao.AlocacaoFacade;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.professor.ProfessorFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudAlocacaoBean extends AbstractCrudBean<Alocacao> {

    private ProfessorFacade professorFacade = ServiceLocator.getFacade(ProfessorFacade.class);
    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    private TurmaFacade turmaFacade = ServiceLocator.getFacade(TurmaFacade.class);
    private DisciplinaFacade disciplinaFacade = ServiceLocator.getFacade(DisciplinaFacade.class);
    private AlocacaoFacade alocacaoFacade = ServiceLocator.getFacade(AlocacaoFacade.class);

    private Escola escolaSelecionada;
    private Curso cursoSelecionado;
    private Serie serieSelecionada;
    private Turma turmaSelecionada;
    private Periodo periodoSelecionado;
    private Ano anoSelecionado;


    private Boolean editMatricula = Boolean.TRUE;
    private String autoCompleteCachedName;
    private List<Professor> lstAutoCompleteCacheProfessor;

    @PostConstruct
    public void init() {

        autoCompleteCachedName = "***********************************";

        resetBean();

    }

    private void resetBean() {

        setEntity(new Alocacao());
        getEntity().setProfessor(new Professor());
        getEntity().getProfessor().setPessoa(new Pessoa());

        editMatricula = Boolean.TRUE;

        escolaSelecionada = null;
        cursoSelecionado = null;
        serieSelecionada = null;
        turmaSelecionada = null;
        periodoSelecionado = null;
        anoSelecionado = null;

    }

    @Override
    protected void handleCreate(Alocacao entity) {

        try {

            if (!alocacaoFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                alocacaoFacade.inserir(entity);
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
    protected List<Alocacao> handleRetrieve(String searchTerm) {

        try {

            return alocacaoFacade.findByExample(getEntity());

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Alocacao entity) {

        try {

            if (!alocacaoFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                alocacaoFacade.alterar(entity);
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
    protected boolean handleDelete(Alocacao entity) {

        try {

            alocacaoFacade.remover(entity);
            addFacesMessage("remocaoSucesso");
            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

        if (null != getEntity()) {

            editMatricula = Boolean.TRUE;
            
            escolaSelecionada = getEntity().getTurma().getEscola();
            cursoSelecionado = getEntity().getTurma().getSerie().getCurso();
            serieSelecionada = getEntity().getTurma().getSerie();
            turmaSelecionada = getEntity().getTurma();
            periodoSelecionado = getEntity().getTurma().getPeriodo();
            anoSelecionado = getEntity().getTurma().getAno();
            
        }

    }

    @Override
    public void handlePrepareToBack() {

        resetBean();

    }

    public List<Professor> complete(String nome) {

        if ((nome.length() < getAutoCompleteCachedName().length()) || ((nome.length() == getAutoCompleteCachedName().length()) && !nome.equalsIgnoreCase(autoCompleteCachedName))) {

            /*
             * Se o comprimento do nome retornado da view for menor que o anterior ou
             *  tiver o mesmo tamanho e conteúdo diferente a busca deve ser refeita,
             *  pois podem surgir elementos que não estavam na lista anterior.
             */
            setLstAutoCompleteCacheProfessor(professorFacade.findByNome(nome, Boolean.TRUE));

        } else if (nome.length() > getAutoCompleteCachedName().length()) {

            /*
             * Caso contrário, tudo que é necessário é refinar a busca dentro da
             *  lista de professores que já possuímos.
             */
            updateListAutoCompleteCacheProfessor(nome);

        }

        setAutoCompleteCachedName(nome);

        return getLstAutoCompleteCacheProfessor();

    }

    private void updateListAutoCompleteCacheProfessor(String nome) {

        for (int i = 0; i < getLstAutoCompleteCacheProfessor().size(); i++) {

            Professor p = getLstAutoCompleteCacheProfessor().get(i);

            if (!p.getPessoa().getNome().toLowerCase().startsWith(nome.toLowerCase())) {

                getLstAutoCompleteCacheProfessor().remove(p);

            }

        }

    }

    public void alterarProfessorSelecionado() {

//        resetBean();
        this.setEditMatricula(Boolean.TRUE);

    }

    public void populaCampos(SelectEvent event) {

        if (this.getEditMatricula().equals(Boolean.TRUE)) {

            this.setEditMatricula(Boolean.FALSE);

        } else {

            this.setEditMatricula(Boolean.TRUE);

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

        if (null == getEscolaSelecionada()) {

            return Collections.EMPTY_LIST;

        }

        List<SelectItem> itens = new ArrayList<SelectItem>();

        try {

            List<Curso> cursos;

            if (null == periodoSelecionado || null == anoSelecionado) {

                cursos = cursoFacade.findByEscola(getEscolaSelecionada());

            } else {

                cursos = cursoFacade.findByEscolaSemestreEAno(getEscolaSelecionada(),periodoSelecionado, anoSelecionado);

            }

            for (Curso curso : cursos) {

                itens.add(new SelectItem(curso, curso.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaCurso.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaSerie() {

        try {

            if (null == getCursoSelecionado()) {

                return Collections.EMPTY_LIST;

            }

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Serie s : getCursoSelecionado().getListaSeries()) {

                itens.add(new SelectItem(s, s.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaSerie.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaTurma() {

        try {

            if (null == getEscolaSelecionada() || null == getCursoSelecionado() || null == getSerieSelecionada()) {

                return Collections.EMPTY_LIST;

            }

            List<SelectItem> itens = new ArrayList<SelectItem>();
            List<Turma> turmas;

            if (null == periodoSelecionado || null == anoSelecionado) {

                turmas = turmaFacade.findByEscolaCursoSerie( getEscolaSelecionada(), getCursoSelecionado(), getSerieSelecionada());

            } else {

                turmas = turmaFacade.findByEscolaCursoSerieAnoSemestre( getEscolaSelecionada(), getCursoSelecionado(), getSerieSelecionada(), getAnoSelecionado(), getPeriodoSelecionado());
                
            }

            for (Turma t : turmas) {

                itens.add(new SelectItem(t, t.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaTurma.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public List<SelectItem> getListaDisciplina() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Disciplina d : disciplinaFacade.findAll()) {

                itens.add(new SelectItem(d, d.getDescricao()));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.getListaDisciplina.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    public Boolean getEditMatricula() {
        return editMatricula;
    }

    public void setEditMatricula(Boolean editMatricula) {
        this.editMatricula = editMatricula;
    }

    public String getAutoCompleteCachedName() {
        return autoCompleteCachedName;
    }

    public void setAutoCompleteCachedName(String autoCompleteCachedName) {
        this.autoCompleteCachedName = autoCompleteCachedName;
    }

    public List<Professor> getLstAutoCompleteCacheProfessor() {
        return lstAutoCompleteCacheProfessor;
    }

    public void setLstAutoCompleteCacheProfessor(List<Professor> lstAutoCompleteCacheProfessor) {
        this.lstAutoCompleteCacheProfessor = lstAutoCompleteCacheProfessor;
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

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Serie getSerieSelecionada() {
        return serieSelecionada;
    }

    public void setSerieSelecionada(Serie serieSelecionada) {
        this.serieSelecionada = serieSelecionada;
    }

    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }

    public Escola getEscolaSelecionada() {
        return escolaSelecionada;
    }

    public void setEscolaSelecionada(Escola escolaSelecionada) {
        this.escolaSelecionada = escolaSelecionada;
    }

}
