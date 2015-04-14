/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.aluno.AlunoFacade;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.matricula.MatriculaFacade;
import br.com.r2jb.siscad.business.facade.serie.SerieFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.util.SituacaoMatricula;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author joaquim
 */
@ManagedBean
@ViewScoped
public class MatricularAlunoBean extends BaseBean {

    private MatriculaFacade matriculaFacade = ServiceLocator.getFacade(MatriculaFacade.class);
    private TurmaFacade turmaFacade = ServiceLocator.getFacade(TurmaFacade.class);
    private AlunoFacade alunoFacade = ServiceLocator.getFacade(AlunoFacade.class);
    private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    private SerieFacade serieFacade = ServiceLocator.getFacade(SerieFacade.class);
    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);

    private Matricula matricula;

    private Escola escolaSelecionada;
    private Curso cursoSelecionado;
    private Serie serieSelecionado;

    private String autoCompleteCachedName;
    private List<Aluno> lstAutoCompleteCacheAluno;
    private Boolean editRa = Boolean.TRUE;
    private Boolean exibeTurmasAbertas = Boolean.FALSE;
    private Boolean manutencao = Boolean.FALSE;

    @PostConstruct
    public void postConstruct() {

        autoCompleteCachedName = "***********************************";

        resetBean();

    }

    /**
     * Persiste as alterações efetuadas nos dados da matrícula
     * 
     */
    public void alterarMatriculaAluno() {

        try {

            if (usuarioLogadoPodeMatricularAluno()) {

                if (null != matricula.getSituacaoMatricula()) {

                    matriculaFacade.alterarMatricula(getMatricula());
                    resetBean();
                    this.editRa = Boolean.TRUE;
                    addFacesMessage("matriculaAlteradaSucesso");

                } else {

                    addFacesMessage("errorInformacaoPendente");

                }

            } else {

                addFacesMessage("msgAcessoNegado");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "MAB.alterarMatriculaAluno.exception", e);

        }

    }

    /**
     * Persiste a matrícula do aluno
     *
     */
    public void matricularAluno() {

        try {

            if (usuarioLogadoPodeMatricularAluno()) {

                if (null != getMatricula() && null != getMatricula().getAluno() && getMatricula().getTurma() != null && null != getMatricula().getDataentrada()) {

                    matriculaFacade.registrarMatricula(getMatricula());
                    resetBean();
                    this.editRa = Boolean.TRUE;
                    addFacesMessage("matriculadoSucesso");

                } else {

                    addFacesMessage("errorInformacaoPendente");

                }

            } else {

                addFacesMessage("msgAcessoNegado");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "MAB.matricularAluno.exception", e);



        }

    }

    private boolean usuarioLogadoPodeMatricularAluno() {

        Usuario usuario = (Usuario) getSession().getAttribute(LoginBean.SESSION_USER_KEY);

        if (null != usuario) {

            return usuario.getPerfil().getTipoPerfil().aoMenosSecretario();

        }

        return false;

    }

    /**
     *
     * @return lista que popula o selectOneMenu escolas
     */
    public List<SelectItem> getEscolas() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Escola escola : escolaFacade.findAll()) {

                itens.add(new SelectItem(escola));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RNB.getEscolas.Exception", e);

            return Collections.EMPTY_LIST;

        }

    }

    /**
     *
     * @return lista que popula o selectOneMenu cursos
     */
    public List<SelectItem> getCursos() {

        try {

            if (null != escolaSelecionada) {

                List<SelectItem> itens = new ArrayList<SelectItem>();

                for (Curso curso : cursoFacade.findByEscola(escolaSelecionada)) {

                    itens.add(new SelectItem(curso));

                }

                return itens;

            }

            return Collections.emptyList();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RNB.getCursos.Exception", e);

            return Collections.EMPTY_LIST;

        }

    }

    /**
     *
     * @return lista que popula o selectOneMenu series
     */
    public List<SelectItem> getSeries() {

        try {

            if (null != escolaSelecionada && null != cursoSelecionado) {

                List<SelectItem> itens = new ArrayList<SelectItem>();

                for (Serie serie : serieFacade.findSeriePorEscolaECurso(escolaSelecionada, cursoSelecionado)) {

                    itens.add(new SelectItem(serie));

                }

                if (null != matricula && null != matricula.getTurma() && null != matricula.getTurma().getSerie()) {

                    serieSelecionado = matricula.getTurma().getSerie();

                }

                return itens;

            }

            return Collections.emptyList();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RNB.getSeries.Exception", e);

            return Collections.EMPTY_LIST;

        }
    }

    /**
     *
     * @return lista que popula o selectOneMenu turmas
     */
    public List<SelectItem> getTurmas() {

        try {

            if (null != escolaSelecionada && null != cursoSelecionado && null != serieSelecionado) {

                List<SelectItem> itens = new ArrayList<SelectItem>();

                for (Turma turma : turmaFacade.findAbertasEFechadasByEscolaCursoSerie(escolaSelecionada, cursoSelecionado, serieSelecionado)) {

                    itens.add(new SelectItem(turma, getSelectItemLabel(turma)));

                }

                return itens;

            }

            return Collections.emptyList();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RNB.getTurmas.Exception", e);

            return Collections.EMPTY_LIST;

        }

    }

    public String getSelectItemLabel(Turma turma) {

        if (null == turma) {

            return null;

        }

        return turma.getDescricao() + ": " + turma.getPeriodo().getDescricao(getLocale()) + " - " + turma.getAno().getValor();

    }

    public List<SelectItem> getSituacoesMatricula() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (SituacaoMatricula st : SituacaoMatricula.values()) {

            itens.add(new SelectItem(st, st.getDescricao(getLocale())));

        }

        return itens;

    }

    public void verificaRA() {

        String sRa = Integer.toString(this.getMatricula().getAluno().getRa());

        if (null == alunoFacade.findByRA(sRa)) {

            addFacesMessage("raInvalido");

        }

    }

    public List<Aluno> complete(String nome) {

        if ((nome.length() < autoCompleteCachedName.length()) || ((nome.length() == autoCompleteCachedName.length()) && !nome.equalsIgnoreCase(autoCompleteCachedName))) {

            /*
             * Se o comprimento do nome retornado da view for menor que o anterior ou
             *  tiver o mesmo tamanho e conteúdo diferente a busca deve ser refeita,
             *  pois podem surgir elementos que não estavam na lista anterior.
             */
            lstAutoCompleteCacheAluno = alunoFacade.findByNome(nome, Boolean.TRUE);

        } else if (nome.length() > autoCompleteCachedName.length()) {

            /*
             * Caso contrário, tudo que é necessário é refinar a busca dentro da
             *  lista de alunos que já possuímos.
             */
            updateListAutoCompleteCacheAluno(nome);

        }

        autoCompleteCachedName = nome;

        return lstAutoCompleteCacheAluno;

    }

    private void updateListAutoCompleteCacheAluno(String nome) {

        for (int i = 0; i < lstAutoCompleteCacheAluno.size(); i++) {

            Aluno a = lstAutoCompleteCacheAluno.get(i);

            if (!a.getNome().toLowerCase().startsWith(nome.toLowerCase())) {

                lstAutoCompleteCacheAluno.remove(a);

            }

        }

    }

    public void preencheDadosManutencaoMatricula() {

        Matricula matriculaMaisRecente = matriculaFacade.findMatriculaMaisRecenteByAluno(matricula.getAluno());

        if (null != matriculaMaisRecente) {

            matricula = matriculaMaisRecente;
            this.editRa = Boolean.FALSE;

        } else {

            resetBean();
            this.editRa = Boolean.TRUE;
            addFacesMessage("alunoNaoPossuiMatricula");

        }

    }

    public void preencheDadosInclusaoMatricula() {

        Matricula matriculaMaisRecente = matriculaFacade.findMatriculaMaisRecenteByAluno(matricula.getAluno());
        matricula.setDataentrada(new Date());

        if (null != matriculaMaisRecente) {

            final SituacaoMatricula situacaoMatriculaMaisRecente = matriculaMaisRecente.getSituacaoMatricula();

            if (SituacaoMatricula.MATRICULADO.equals(situacaoMatriculaMaisRecente)) {

                resetBean();
                this.editRa = Boolean.TRUE;
                addFacesMessage("alunoJaMatriculado");

            } else {

                escolaSelecionada = matriculaMaisRecente.getTurma().getEscola();
                cursoSelecionado = matriculaMaisRecente.getTurma().getSerie().getCurso();

                if (SituacaoMatricula.APROVADO.equals(situacaoMatriculaMaisRecente)) {

                    serieSelecionado = matriculaMaisRecente.getTurma().getSerie().getSerieSucessora();

                } else if (SituacaoMatricula.RETIDO.equals(situacaoMatriculaMaisRecente)) {

                    serieSelecionado = matriculaMaisRecente.getTurma().getSerie();

                }

                this.editRa = Boolean.FALSE;

            }

        } else {

            this.editRa = Boolean.FALSE;

        }

    }

    public void alterarAlunoSelecionado() {

        resetBean();
        this.editRa = Boolean.TRUE;

    }

    public void populaCampos(SelectEvent event) {

        if (this.editRa.equals(Boolean.TRUE)) {

            if (getManutencao()) {

                preencheDadosManutencaoMatricula();

            } else {

                preencheDadosInclusaoMatricula();

            }

        }

    }

    private void resetBean() {

        this.escolaSelecionada = new Escola();
        this.cursoSelecionado = new Curso();
        this.serieSelecionado = new Serie();

        this.setMatricula(new Matricula());
        this.getMatricula().setAluno(new Aluno());

    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Escola getEscolaSelecionada() {
        return escolaSelecionada;
    }

    public void setEscolaSelecionada(Escola escolaSelecionada) {
        this.escolaSelecionada = escolaSelecionada;
    }

    public Serie getSerieSelecionado() {
        return serieSelecionado;
    }

    public void setSerieSelecionado(Serie serieSelecionado) {
        this.serieSelecionado = serieSelecionado;
    }

    public Boolean getEditRa() {
        return editRa;
    }

    public void setEditRa(Boolean editRa) {
        this.editRa = editRa;
    }

    public Boolean getExibeTurmasAbertas() {
        return exibeTurmasAbertas;
    }

    public void setExibeTurmasAbertas(Boolean exibeTurmasAbertas) {
        this.exibeTurmasAbertas = exibeTurmasAbertas;
    }

    public Matricula getMatricula() {

        if (null == matricula) {

            matricula = new Matricula();
            matricula.setAluno(new Aluno());

        }

        return matricula;

    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Boolean getManutencao() {
        return manutencao;
    }

    public void setManutencao(Boolean manutencao) {
        this.manutencao = manutencao;
    }

}
