/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.CodigoFuncao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "responsavel")
public class Responsavel extends TipoFuncao {

    private static final long serialVersionUID = 1L;

    public static final String PROFISSAO = "profissao";
    public static final String LOCAL_TRABALHO = "localTrabalho";
    public static final String RENDA = "renda";
    public static final String ALUNOS = "alunos";

    @Basic(optional = false)
    @Column(name = "profissao")
    private String profissao;

    @Basic(optional = false)
    @Column(name = "localtrabalho")
    private String localTrabalho;

    @Basic(optional = false)
    @Column(name = "renda")
    private float renda;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "responsavel")
    private List<Aluno> listaAluno =  new ArrayList<Aluno>();

    public Responsavel() {
        setCodigoFuncao(CodigoFuncao.RESPONSAVEL);
    }

    public Responsavel(Integer idTipoFuncao) {
        super(idTipoFuncao,CodigoFuncao.RESPONSAVEL);
    }

    public Responsavel(Integer idTipoFuncao, String profissao, String localtrabalho, float renda) {
        super(idTipoFuncao,CodigoFuncao.RESPONSAVEL);
        this.profissao = profissao;
        this.localTrabalho = localtrabalho;
        this.renda = renda;
    }

    public boolean possuiDependentes() {

        return !this.listaAluno.isEmpty();

    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localtrabalho) {
        this.localTrabalho = localtrabalho;
    }

    public float getRenda() {
        return renda;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public List<Aluno> getListaAluno() {

        return Collections.unmodifiableList(listaAluno);

    }

    public void internalAddAluno(Aluno aluno) {

        if (listaAluno.equals(Collections.EMPTY_LIST))
            listaAluno = new ArrayList<Aluno>();
        listaAluno.add(aluno);
        
    }

    public void internalRemoveAluno(Aluno aluno) {

        listaAluno.remove(aluno);

    }

    public void addAluno(Aluno aluno) {

        aluno.setResponsavel(this);

    }

    public void removeAluno(Aluno aluno) {

        aluno.setResponsavel(null);

    }

    public void setListaAluno(List<Aluno> listaAluno) {
        this.listaAluno = listaAluno;
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Responsavel[idpessoa=" + getId() + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Responsavel responsavel = (Responsavel) obj;

        return new EqualsBuilder()
                    .append(super.getId(), responsavel.getId())
                    .append(this.localTrabalho, responsavel.localTrabalho)
                    .append(this.profissao, responsavel.profissao)
                    .append(this.renda, responsavel.renda)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(super.getId())
                     .append(this.localTrabalho)
                     .append(this.profissao)
                     .append(this.renda)
                     .toHashCode();

    }

}
