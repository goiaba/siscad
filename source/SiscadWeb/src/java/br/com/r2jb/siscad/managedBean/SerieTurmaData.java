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
public class SerieTurmaData implements Serializable {

    private Turma turma;
    private String descricao;
    private List<Disciplina> disciplinas;
    private List<SerieTurmaData.MatriculaNota> listaMatriculaNota;
    private List<PeriodoAvaliacao> periodosAvaliacao;

    public SerieTurmaData(List<PeriodoAvaliacao> periodosAvaliacao, List<Disciplina> disciplinas, Turma turma) {

        this.turma = turma;
        this.descricao = turma.getSerie().getDescricao() + " " + turma.getCodigo();
        this.disciplinas = disciplinas;
        this.periodosAvaliacao = periodosAvaliacao;
        this.listaMatriculaNota = new ArrayList<MatriculaNota>();

    }
    
    public void addMatriculaNota(Matricula matricula, Float nota, Boolean isPrimeiroRegistro) {
        
        getListaMatriculaNota().add(new MatriculaNota(matricula, nota, isPrimeiroRegistro));
        
    }
    
    public void addMatriculaNota(Matricula matricula) {
        
        getListaMatriculaNota().add(new MatriculaNota(matricula, null, Boolean.TRUE));
        
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
    public List<MatriculaNota> getListaMatriculaNota() {
        return listaMatriculaNota;
    }

    /**
     * @param listaMatriculaNota the listaMatriculaNota to set
     */
    public void setListaMatriculaNota(List<MatriculaNota> listaMatriculaNota) {
        this.listaMatriculaNota = listaMatriculaNota;
    }

    /**
     * Classe auxiliar, para facilitar o registro das notas.
     */
    public class MatriculaNota implements Serializable {

        private Matricula matricula = null;
        private Float nota = null;
        private Boolean primeiroRegistro = Boolean.FALSE;

        public MatriculaNota(Matricula matricula, Float nota, Boolean isPrimeiroRegistro) {

            this.matricula = matricula;
            this.nota = nota;
            this.primeiroRegistro = isPrimeiroRegistro;

        }

        public MatriculaNota(Matricula matricula) {
            
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
        public Float getNota() {
            return nota;
        }

        /**
         * @param nota the nota to set
         */
        public void setNota(Float nota) {
            this.nota = nota;
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
