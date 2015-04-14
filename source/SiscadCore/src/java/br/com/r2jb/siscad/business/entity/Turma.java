/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "turma")
public class Turma extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TURMA_ID_GEN", sequenceName="turma_idturma_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TURMA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idturma")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "semestre")
    @Enumerated(value = EnumType.STRING)
    private Periodo periodo;

    @Basic(optional = false)
    @Column(name = "ano")
    @Enumerated(value = EnumType.STRING)
    private Ano ano;

    @Basic(optional = false)
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusTurma status;
    
    @JoinColumn(name = "idturno", referencedColumnName = "idturno")
    @ManyToOne(optional = false)
    private Turno turno;

    @JoinColumn(name = "idserie", referencedColumnName = "idserie")
    @ManyToOne(optional = false)
    private Serie serie;

    @JoinColumn(name = "idescola", referencedColumnName = "idescola")
    @ManyToOne(optional = false)
    private Escola escola;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "turma")
    private List<Matricula> listaMatricula =  new ArrayList<Matricula>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma")
    private List<Alocacao> listaAlocacao =  new ArrayList<Alocacao>();

    public Turma() {
    }

    public Turma(Integer idturma) {
        this.id = idturma;
    }

    public Turma(Integer idturma, String descricao, String codigo, Periodo periodo, Ano ano, StatusTurma status) {
        this.id = idturma;
        this.descricao = descricao;
        this.codigo = codigo;
        this.periodo = periodo;
        this.ano = ano;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idturma) {
        this.id = idturma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public StatusTurma getStatus() {
        return status;
    }

    public void setStatus(StatusTurma status) {
        this.status = status;
    }

    public List<Matricula> getListaMatricula() {
        return listaMatricula;
    }

    public void setListaMatricula(List<Matricula> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public List<Alocacao> getListaAlocacao() {
        return listaAlocacao;
    }

    public void setListaAlocacao(List<Alocacao> listaAlocacao) {
        this.listaAlocacao = listaAlocacao;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Turma turma = (Turma) obj;

        return new EqualsBuilder()
                    .append(this.codigo, turma.codigo)
                    .append(this.descricao, turma.descricao)
                    .append(this.id, turma.id)
                    .append(this.periodo, turma.periodo)
                    .append(this.status, turma.status)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.codigo)
                    .append(this.descricao)
                    .append(this.status)
                    .append(this.periodo)
                    .toHashCode();

    }

    @Override
    public String toString() {

        return this.getDescricao();
        
    }

}
