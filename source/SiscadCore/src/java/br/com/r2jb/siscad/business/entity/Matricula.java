/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.SituacaoMatricula;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "matricula")
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByDataentrada", query = "SELECT m FROM Matricula m WHERE m.dataentrada = :dataentrada"),
    @NamedQuery(name = "Matricula.findByNumeroordem", query = "SELECT m FROM Matricula m WHERE m.numeroordem = :numeroordem"),
    @NamedQuery(name = "Matricula.findByDatasaida", query = "SELECT m FROM Matricula m WHERE m.datasaida = :datasaida")})
public class Matricula extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="MATRICULA_ID_GEN", sequenceName="matricula_idmatricula_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATRICULA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idmatricula")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "dataentrada")
    @Temporal(TemporalType.DATE)
    private Date dataentrada;

    @Basic(optional = false)
    @Column(name = "numeroordem")
    private int numeroordem;

    @Basic(optional = true)
    @Column(name = "datasaida")
    @Temporal(TemporalType.DATE)
    private Date datasaida;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<Nota> listaNota =  new ArrayList<Nota>();

    @JoinColumn(name = "idturma", referencedColumnName = "idturma")
    @ManyToOne(optional = false)
    private Turma turma;

    @JoinColumn(name = "idaluno", referencedColumnName = "idaluno")
    @ManyToOne(optional = false)
    private Aluno aluno;

    @Basic(optional = false)
    @Column(name = "situacao")
    @Enumerated(value = EnumType.STRING)
    private SituacaoMatricula situacaoMatricula;

    public Matricula() {
    }

    public Matricula(Integer idmatricula) {
        this.id = idmatricula;
    }

    public Matricula(Integer idmatricula, Date dataentrada, int numeroordem, Date datasaida) {
        this.id = idmatricula;
        this.dataentrada = dataentrada;
        this.numeroordem = numeroordem;
        this.datasaida = datasaida;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idmatricula) {
        this.id = idmatricula;
    }

    public Date getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(Date dataentrada) {
        this.dataentrada = dataentrada;
    }

    public int getNumeroordem() {
        return numeroordem;
    }

    public void setNumeroordem(int numeroordem) {
        this.numeroordem = numeroordem;
    }

    public Date getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(Date datasaida) {
        this.datasaida = datasaida;
    }

    public List<Nota> getListaNota() {
        return listaNota;
    }

    public void setListaNota(List<Nota> listaNota) {
        this.listaNota = listaNota;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the situacaoMatricula
     */
    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    /**
     * @param situacaoMatricula the situacaoMatricula to set
     */
    public void setSituacaoMatricula(SituacaoMatricula situacaoMatricula) {
        this.situacaoMatricula = situacaoMatricula;
    }

//    /**
//     * @return the situacaoMatricula
//     */
//    public ConfiguracaoGlobal getSituacaoMatricula() {
//        return situacaoMatricula;
//    }
//
//    /**
//     * @param situacaoMatricula the situacaoMatricula to set
//     */
//    public void setSituacaoMatricula(ConfiguracaoGlobal situacaoMatricula) {
//
//        if (null == situacaoMatricula || !situacaoMatricula.getDomain().equals(Domain.SITUACAO_MATRICULA)) {
//
//            throw new InvalidGlobalConfigurationInContextException();
//
//        }
//
//        this.situacaoMatricula = situacaoMatricula;
//
//    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Matricula[idmatricula=" + id + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Matricula matricula = (Matricula) obj;

        return new EqualsBuilder()
                    .append(this.id, matricula.id)
                    .append(this.numeroordem, matricula.numeroordem)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.numeroordem)
                    .toHashCode();

    }

}
