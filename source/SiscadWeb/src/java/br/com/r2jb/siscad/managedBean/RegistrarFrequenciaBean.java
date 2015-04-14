/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Frequencia;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.frequencia.FrequenciaFacade;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import br.com.r2jb.siscad.business.facade.professor.ProfessorFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.managedBean.SerieTurmaFrequenciaData.MatriculaFrequencia;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import br.com.r2jb.siscad.business.ServiceLocator;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class RegistrarFrequenciaBean extends BaseBean {
    
    private FrequenciaFacade frequenciaFacade = ServiceLocator.getFacade(FrequenciaFacade.class);
    private TurmaFacade turmaFacade = ServiceLocator.getFacade(TurmaFacade.class);
    private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    private ProfessorFacade professorFacade = ServiceLocator.getFacade(ProfessorFacade.class);
    private DisciplinaFacade disciplinaFacade = ServiceLocator.getFacade(DisciplinaFacade.class);
    private PeriodoAvaliacaoFacade periodoAvaliacaoFacade = ServiceLocator.getFacade(PeriodoAvaliacaoFacade.class);

    private Professor professor;

    private Escola escolaSelecionada;
    private Curso cursoSelecionado;
    private SerieTurmaFrequenciaData serieTurmaSelecionada;
    private Disciplina disciplinaSelecionada;
    private PeriodoAvaliacao periodoFrequenciaSelecionado;
    
    private Ano anoSelecionado;
    private Periodo semestreSelecionado;

    private ListDataModel<SerieTurmaFrequenciaData> listaSerieTurma;

    private Boolean selectPeriodoFrequenciaHabilitado = Boolean.FALSE;
    private Boolean tabelaTurmasVisivel = Boolean.FALSE;

    @PostConstruct
    public void init() {
        
        try {

            professor = getProfessorLogado();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RNB.registrarNotaBean", e);

        }
        
    }
    
    public void registrarFrequencias() {

        try {
            
            if (usuarioLogadoPodeRegistrarFrequencia()) {

                if (null != periodoFrequenciaSelecionado && null != disciplinaSelecionada) {

                    List<Frequencia> frequencias = new ArrayList<Frequencia>();

                    for (MatriculaFrequencia alunoFrequencia : serieTurmaSelecionada.getListaMatriculaFrequencia()) {

                        if (alunoFrequencia.getPrimeiroRegistro() && (null != alunoFrequencia.getFrequencia())) {

                            Frequencia frequencia = new Frequencia();

                            // @FIXME - Checar PeriodoAvaliacao (frequencia nao é por avaliacao)
                            frequencia.setPeriodoAvaliacao(periodoFrequenciaSelecionado);
                            frequencia.setDisciplina(disciplinaSelecionada);
                            frequencia.setMatricula(alunoFrequencia.getMatricula());
                            frequencia.setValor(alunoFrequencia.getFrequencia());

                            frequencias.add(frequencia);

                        }

                    }

                    if (frequencias.isEmpty()) {

                        addFacesMessage("nenhumaFrequenciaRegistrada", FacesMessage.SEVERITY_INFO);

                    } else {

                        frequenciaFacade.registrarFrequencias(frequencias);
                        addFacesMessage("frequenciasRegistradasComSucesso", FacesMessage.SEVERITY_INFO);

                    }

                }
                
            } else {
                
                addFacesMessage("msgAcessoNegado");
                
            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.registrarFrequencias.exception", e);

        }

    }

    /**
     * Faz a busca das turmas de acordo com os parâmetros de pesquisa.
     * 
     * @return view a exibir
     */
    public String buscar() {

        try {

            List<Turma> turmas = turmaFacade.findByProfessorEscolaCursoSemestreEAno(professor, escolaSelecionada, cursoSelecionado, semestreSelecionado, anoSelecionado);

            List<SerieTurmaFrequenciaData> lstSerieTurma = new ArrayList<SerieTurmaFrequenciaData>();
            
            for (Turma turma : turmas) {

                List<Disciplina> disciplinas = null;

                if (null == professor) {

                    disciplinas = disciplinaFacade.findByTurma(turma);

                } else {

                    disciplinas = disciplinaFacade.findByProfessorETurma(professor, turma);

                }

                lstSerieTurma.add(new SerieTurmaFrequenciaData(turma.getSerie().getCurso().getListaPeriodoAvaliacao(), disciplinas, turma));

            }

            if (null == lstSerieTurma || lstSerieTurma.isEmpty()) {

                setTabelaTurmasVisivel(Boolean.FALSE);

            } else {

                listaSerieTurma = new ListDataModel<SerieTurmaFrequenciaData>(lstSerieTurma);
                periodoFrequenciaSelecionado = null;
                disciplinaSelecionada = null;

                setSelectPeriodoFrequenciaHabilitado(Boolean.FALSE);
                setTabelaTurmasVisivel(Boolean.TRUE);

            }

            return null;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.buscar.exception", e);
            return null;

        }

    }
    
    /**
     *
     * @return String que representa o título do dialog de registro de notas
     */
    public String getTituloDialogFrequencias() {

        StringBuilder retorno = new StringBuilder();

        if (null != escolaSelecionada) {

            retorno.append(escolaSelecionada.getNome())
                    .append(" - ");

        }

        if (null != cursoSelecionado) {

            retorno.append(cursoSelecionado.getDescricao())
                    .append(" - ");

        }

        if (null != serieTurmaSelecionada) {

            retorno.append(serieTurmaSelecionada.getDescricao())
            .append(" - ");

        }

        if (null != disciplinaSelecionada) {

            retorno.append(disciplinaSelecionada.getDescricao())
                    .append(" - ");

        }

        if (null != periodoFrequenciaSelecionado) {

            retorno.append(periodoFrequenciaSelecionado.getDescricao())
                    .append(" - ")
                    .append(periodoFrequenciaSelecionado.getDescricao());

        }

        return retorno.toString();

    }

    /**
     * Listener relacionado aos selectOneMenu. Apenas esconde a tabela no caso
     *  de alteração nos parâmetros de pesquisa.
     */
    public void selectListener() {

        setTabelaTurmasVisivel(Boolean.FALSE);

    }

    /**
     * Listener relacionado ao selectOneMenu disciplinas
     */
    public void selectDisciplinasListener() {

        if (null != disciplinaSelecionada) {

            setSelectPeriodoFrequenciaHabilitado(Boolean.TRUE);

        } else {

            setSelectPeriodoFrequenciaHabilitado(Boolean.FALSE);
            periodoFrequenciaSelecionado = null;

        }

    }
    
    public void frequenciaListener() {

        try {

            serieTurmaSelecionada = listaSerieTurma.getRowData();
            
            serieTurmaSelecionada.getListaMatriculaFrequencia().clear();

            for (Matricula matricula : serieTurmaSelecionada.getTurma().getListaMatricula()) {

                Float frequencia = frequenciaFacade.isFrequenciaJaRegistrada(matricula ,disciplinaSelecionada, periodoFrequenciaSelecionado);

                if (null != frequencia) {

                    serieTurmaSelecionada.addMatriculaFrequencia(matricula, frequencia, Boolean.FALSE);

                } else {

                    serieTurmaSelecionada.addMatriculaFrequencia(matricula);

                }

            }

        } catch (Exception ex) {

            addFacesMessage("erroInesperado");
            Logger.getLogger(SerieTurmaData.class.getName()).log(Level.SEVERE, "STB.populateListaAlunos.Exception", ex);

        }

    }
    
    /**
     *
     * @return lista que popula o selectOneMenu escolas
     */
    public List<SelectItem> getEscolas() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Escola escola : escolaFacade.findByProfessorCursoSemestreEAno(professor, cursoSelecionado, semestreSelecionado, anoSelecionado)) {

                itens.add(new SelectItem(escola));

            }

            return itens;


        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.getEscolas.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    /**
     *
     * @return lista que popula o selectOneMenu cursos
     */
    public List<SelectItem> getCursos() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Curso curso : cursoFacade.findByProfessorEscolaSemestreEAno(professor, escolaSelecionada, semestreSelecionado, anoSelecionado)) {

                itens.add(new SelectItem(curso));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.getCursos.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    /**
     * 
     * @return lista que popula o selectOneMenu semestres
     */
    public List<SelectItem> getSemestres() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Periodo semestre : periodoAvaliacaoFacade.findSemestres(professor, escolaSelecionada, cursoSelecionado, anoSelecionado)) {

                itens.add(new SelectItem(semestre, semestre.getDescricao(getLocale())));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.getSemestres.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }

    /**
     * 
     * @return lista que popula o selectOneMenu anos
     */
    public List<SelectItem> getAnos() {

        try {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Ano ano : periodoAvaliacaoFacade.findAnos(professor, escolaSelecionada, cursoSelecionado, semestreSelecionado)) {

                itens.add(new SelectItem(ano, Integer.toString(ano.getValor())));

            }

            return itens;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "RFB.getAnos.Exception", e);
            return Collections.EMPTY_LIST;

        }

    }
    
    /**
     * 
     * @return lista que popula o selectOneMenu disciplinas
     */
    public List<SelectItem> getDisciplinas() {

        serieTurmaSelecionada = listaSerieTurma.getRowData();
        
        if (null != serieTurmaSelecionada.getDisciplinas()) {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (Disciplina disc : serieTurmaSelecionada.getDisciplinas()) {

                itens.add(new SelectItem(disc));

            }

            return itens;

        }

        return Collections.EMPTY_LIST;

    }

    public List<SelectItem> getPeriodoFrequencias() {
        
        serieTurmaSelecionada = listaSerieTurma.getRowData();

        if (null != serieTurmaSelecionada) {

            List<SelectItem> itens = new ArrayList<SelectItem>();

            for (PeriodoAvaliacao perAval : serieTurmaSelecionada.getTurma().getSerie().getCurso().getListaPeriodoAvaliacao()) {

                itens.add(new SelectItem(perAval));

            }

            return itens;

        }

        return Collections.EMPTY_LIST;

    }

    /**
     *
     * @return O professor logado, no caso do usuário ser do tipo Professor
     *          ou null, caso contrário.
     */
    private Professor getProfessorLogado() {

        Usuario usuarioLogado = (Usuario) getSession().getAttribute(LoginBean.SESSION_USER_KEY);

        if (null != usuarioLogado && usuarioLogado.isProfessor()) {

            return professorFacade.find(usuarioLogado.getPessoa().getId());

        }

        return null;

    }

    private boolean usuarioLogadoPodeRegistrarFrequencia() {

        Usuario usuario = (Usuario) getSession().getAttribute(LoginBean.SESSION_USER_KEY);

        if (null != usuario) {

            return usuario.getPerfil().getTipoPerfil().aoMenosProfessor();

        }

        return false;

    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * @return the escolaSelecionada
     */
    public Escola getEscolaSelecionada() {
        return escolaSelecionada;
    }

    /**
     * @param escolaSelecionada the escolaSelecionada to set
     */
    public void setEscolaSelecionada(Escola escolaSelecionada) {
        this.escolaSelecionada = escolaSelecionada;
    }

    /**
     * @return the cursoSelecionado
     */
    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    /**
     * @param cursoSelecionado the cursoSelecionado to set
     */
    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    /**
     * @return the tabelaTurmasVisivel
     */
    public Boolean getTabelaTurmasVisivel() {
        return tabelaTurmasVisivel;
    }

    /**
     * @param tabelaTurmasVisivel the tabelaTurmasVisivel to set
     */
    public void setTabelaTurmasVisivel(Boolean tabelaTurmasVisivel) {
        this.tabelaTurmasVisivel = tabelaTurmasVisivel;
    }

    /**
     * @return the serieTurmaSelecionada
     */
    public SerieTurmaFrequenciaData getSerieTurmaSelecionada() {
        return serieTurmaSelecionada;
    }

    /**
     * @param serieTurmaSelecionada the serieTurmaSelecionada to set
     */
    public void setSerieTurmaSelecionada(SerieTurmaFrequenciaData serieTurmaSelecionada) {
        this.serieTurmaSelecionada = serieTurmaSelecionada;
    }

    /**
     * @return the disciplinaSelecionada
     */
    public Disciplina getDisciplinaSelecionada() {
        return disciplinaSelecionada;
    }

    /**
     * @param disciplinaSelecionada the disciplinaSelecionada to set
     */
    public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
        this.disciplinaSelecionada = disciplinaSelecionada;
    }

    /**
     * @return the periodoFrequenciaSelecionado
     */
    public PeriodoAvaliacao getPeriodoFrequenciaSelecionado() {
        return periodoFrequenciaSelecionado;
    }

    /**
     * @param periodoFrequenciaSelecionado the periodoFrequenciaSelecionado to set
     */
    public void setPeriodoFrequenciaSelecionado(PeriodoAvaliacao periodoFrequenciaSelecionado) {
        this.periodoFrequenciaSelecionado = periodoFrequenciaSelecionado;
    }

    public Boolean getSelectPeriodoFrequenciaHabilitado() {
        return selectPeriodoFrequenciaHabilitado;
    }

    public void setSelectPeriodoFrequenciaHabilitado(Boolean selectPeriodoFrequenciaHabilitado) {
        this.selectPeriodoFrequenciaHabilitado = selectPeriodoFrequenciaHabilitado;
    }

    /**
     * @return the listaSerieTurma
     */
    public ListDataModel<SerieTurmaFrequenciaData> getListaSerieTurma() {
        return listaSerieTurma;
    }

    /**
     * @param listaSerieTurma the listaSerieTurma to set
     */
    public void setListaSerieTurma(ListDataModel<SerieTurmaFrequenciaData> listaSerieTurma) {
        this.listaSerieTurma = listaSerieTurma;
    }

    /**
     * @return the anoSelecionado
     */
    public Ano getAnoSelecionado() {
        return anoSelecionado;
    }

    /**
     * @param anoSelecionado the anoSelecionado to set
     */
    public void setAnoSelecionado(Ano anoSelecionado) {
        this.anoSelecionado = anoSelecionado;
    }

    /**
     * @return the semestreSelecionado
     */
    public Periodo getSemestreSelecionado() {
        return semestreSelecionado;
    }

    /**
     * @param semestreSelecionado the semestreSelecionado to set
     */
    public void setSemestreSelecionado(Periodo semestreSelecionado) {
        this.semestreSelecionado = semestreSelecionado;
    }

}
