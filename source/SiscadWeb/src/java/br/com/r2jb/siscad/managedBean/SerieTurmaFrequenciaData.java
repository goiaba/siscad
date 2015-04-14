/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Turma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class SerieTurmaFrequenciaData implements Serializable {

    private Turma turma;
    private String descricao;
    private List<Disciplina> disciplinas;
    private List<SerieTurmaFrequenciaData.MatriculaFrequencia> listaMatriculaFrequencia;
    private List<PeriodoAvaliacao> periodosAvaliacao;

    public SerieTurmaFrequenciaData(List<PeriodoAvaliacao> periodosAvaliacao, List<Disciplina> disciplinas, Turma turma) {

        this.turma = turma;
        this.descricao = turma.getSerie().getDescricao() + " " + turma.getCodigo();
        this.disciplinas = disciplinas;
        this.periodosAvaliacao = periodosAvaliacao;
        this.listaMatriculaFrequencia = new ArrayList<MatriculaFrequencia>();

    }

    public void addMatriculaFrequencia(Matricula matricula, Float frequencia, Boolean isPrimeiroRegistro) {

        getListaMatriculaFrequencia().add(new MatriculaFrequencia(matricula, frequencia, isPrimeiroRegistro));

    }

    public void addMatriculaFrequencia(Matricula matricula) {

        getListaMatriculaFrequencia().add(new MatriculaFrequencia(matricula, null, Boolean.TRUE));

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {

        return this.getDescricao();

    }

    /**
     * @return the periodosAvaliacao
     */
    public List<PeriodoAvaliacao> getPeriodosAvaliacao() {
        return periodosAvaliacao;
    }

    /**
     * @param periodosAvaliacao the periodosAvaliacao to set
     */
    public void setPeriodosAvaliacao(List<PeriodoAvaliacao> periodosAvaliacao) {
        this.periodosAvaliacao = periodosAvaliacao;
    }

    /**
     * @return the listaMatriculaNota
     */
    public List<MatriculaFrequencia> getListaMatriculaFrequencia() {
        return listaMatriculaFrequencia;
    }

    /**
     * @param listaMatriculaNota the listaMatriculaNota to set
     */
    public void setListaMatriculaFrequencia(List<MatriculaFrequencia> listaMatriculaFrequencia) {
        this.listaMatriculaFrequencia = listaMatriculaFrequencia;
    }

    /**
     * Classe auxiliar, para facilitar o registro das frequencias.
     */
    public class MatriculaFrequencia implements Serializable {

        private Matricula matricula = null;
        private Float frequencia = null;
        private Boolean primeiroRegistro = Boolean.FALSE;

        public MatriculaFrequencia(Matricula matricula, Float frequencia, Boolean isPrimeiroRegistro) {

            this.matricula = matricula;
            this.frequencia = frequencia;
            this.primeiroRegistro = isPrimeiroRegistro;

        }

        public MatriculaFrequencia(Matricula matricula) {

            this.matricula = matricula;
            this.primeiroRegistro = Boolean.TRUE;

        }

        /**
         * @return the matricula
         */
        public Matricula getMatricula() {
            return matricula;
        }

        /**
         * @param matricula the matricula to set
         */
        public void setMatricula(Matricula matricula) {
            this.matricula = matricula;
        }

        /**
         * @return the nota
         */
        public Float getFrequencia() {
            return frequencia;
        }

        /**
         * @param nota the nota to set
         */
        public void setFrequencia(Float frequencia) {
            this.frequencia = frequencia;
        }

        /**
         * @return true no caso de ser o primeiro registro da nota e false
         *   caso contrário.
         */
        public Boolean getPrimeiroRegistro() {
            return primeiroRegistro;
        }

        /**
         * @param primeiroRegistro the primeiroRegistro to set
         */
        public void setPrimeiroRegistro(Boolean primeiroRegistro) {
            this.primeiroRegistro = primeiroRegistro;
        }

    }

}
