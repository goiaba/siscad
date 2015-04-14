/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "turno")
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByDescricao", query = "SELECT t FROM Turno t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Turno.findByHorainicio", query = "SELECT t FROM Turno t WHERE t.horainicio = :horainicio"),
    @NamedQuery(name = "Turno.findByHoratermino", query = "SELECT t FROM Turno t WHERE t.horatermino = :horatermino")})
public class Turno extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TURNO_ID_GEN", sequenceName="turno_idturno_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TURNO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idturno")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;

    @Basic(optional = false)
    @Column(name = "horatermino")
    @Temporal(TemporalType.TIME)
    private Date horatermino;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turno")
    private List<Turma> listaTurma =  new ArrayList<Turma>();

    public Turno() {
    }

    public Turno(Integer idturno) {
        this.id = idturno;
    }

    public Turno(Integer idturno, String descricao, Date horainicio, Date horatermino) {
        this.id = idturno;
        this.descricao = descricao;
        this.horainicio = horainicio;
        this.horatermino = horatermino;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idturno) {
        this.id = idturno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHoratermino() {
        return horatermino;
    }

    public void setHoratermino(Date horatermino) {
        this.horatermino = horatermino;
    }

    public List<Turma> getLlistaTurma() {
        return listaTurma;
    }

    public void setListaTurma(List<Turma> listaTurma) {
        this.listaTurma = listaTurma;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Turno turno = (Turno) obj;

        return new EqualsBuilder()
                    .append(this.descricao, turno.descricao)
                    .append(this.id, turno.id)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.descricao)
                    .append(this.id)
                    .toHashCode();

    }

    @Override
    public String toString() {

        return this.getDescricao();
        
    }

}
