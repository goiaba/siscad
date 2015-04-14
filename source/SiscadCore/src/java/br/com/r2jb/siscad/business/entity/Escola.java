/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "escola")
@NamedQueries({
    @NamedQuery(name = "Escola.findAll", query = "SELECT e FROM Escola e"),
    @NamedQuery(name = "Escola.findByCnpj", query = "SELECT e FROM Escola e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Escola.findByIe", query = "SELECT e FROM Escola e WHERE e.ie = :ie"),
    @NamedQuery(name = "Escola.findByNome", query = "SELECT e FROM Escola e WHERE e.nome = :nome")})
public class Escola extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ESCOLA_ID_GEN", sequenceName="escola_idescola_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESCOLA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idescola")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;

    @Basic(optional = false)
    @Column(name = "ie")
    private String ie;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name="idendereco")
    private Endereco endereco;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "escola")
    private List<EscolaTelefone> listaEscolaTelefone =  new ArrayList<EscolaTelefone>();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "escola")
    private List<Turma> listaTurma =  new ArrayList<Turma>();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "escola")
    private List<EscolaCurso> listaEscolaCurso =  new ArrayList<EscolaCurso>();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "escola")
    private List<MatrizCurricular> listaMatrizCurricular =  new ArrayList<MatrizCurricular>();

    public Escola() {
    }

    public Escola(Integer idescola) {
        this.id = idescola;
    }

    public Escola(Integer idescola, String cnpj, String ie, String nome) {
        this.id = idescola;
        this.cnpj = cnpj;
        this.ie = ie;
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idescola) {
        this.id = idescola;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("[^0-9]", "");
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<EscolaTelefone> getListaEscolaTelefone() {
        return listaEscolaTelefone;
    }

    public void setListaEscolaTelefone(List<EscolaTelefone> listaEscolaTelefone) {
        this.listaEscolaTelefone = listaEscolaTelefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Turma> getListaTurma() {
        return listaTurma;
    }

    public void setListaTurma(List<Turma> listaTurma) {
        this.listaTurma = listaTurma;
    }

    public List<EscolaCurso> getListaEscolaCurso() {
        return listaEscolaCurso;
    }

    public void setListaEscolaCurso(List<EscolaCurso> listaEscolaCurso) {
        this.listaEscolaCurso = listaEscolaCurso;
    }

    public List<MatrizCurricular> getListaMatrizCurricular() {
        return listaMatrizCurricular;
    }

    public void setListaMatrizCurricular(List<MatrizCurricular> listaMatrizCurricular) {
        this.listaMatrizCurricular = listaMatrizCurricular;
    }

    public void addCurso(Curso curso) {
        
        if (null == listaEscolaCurso) {
            listaEscolaCurso = new ArrayList<EscolaCurso>();
        }
        
        listaEscolaCurso.add(new EscolaCurso(this, curso));        
        
    }
    
    public boolean removeCurso(Curso curso) {
        
        if (null != listaEscolaCurso) {
            
            return listaEscolaCurso.remove(new EscolaCurso(this, curso));
            
        }
        
        return false;

    }

    public List<Curso> getCursos() {

        List<Curso> cursos = new ArrayList<Curso>();

        for (EscolaCurso ec : listaEscolaCurso) {

            cursos.add(ec.getCurso());

        }

        return cursos;
    }

    @Override
    public String toString() {

        return this.getNome();

    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Escola escola = (Escola) obj;

        return new EqualsBuilder()
                    .append(this.cnpj, escola.cnpj)
                    .append(this.ie, escola.ie)
                    .append(this.nome, escola.nome)
                    .append(this.id, escola.id)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(this.id)
                     .append(this.cnpj)
                     .append(this.ie)
                     .append(this.nome)
                     .toHashCode();

    }

}
