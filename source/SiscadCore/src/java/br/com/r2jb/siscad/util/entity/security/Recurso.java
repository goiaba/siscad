/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.entity.security;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="sec_recurso")
public class Recurso extends BaseEntity {

    private Integer id;
    private List<PerfilRecurso> lstPerfilRecurso;
    private TipoRecurso tipoRecurso;
    private String recurso;
    private Boolean seguro;

    @Id
    @SequenceGenerator(name = "RECURSO_ID_GEN", sequenceName = "sec_recurso_idRecurso_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECURSO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idRecurso")
    @Override
    public Integer getId() { return this.id; }
    @Override
    public void setId(Integer id) { this.id = id; }

    @OneToMany(mappedBy = "recurso")
    public List<PerfilRecurso> getLstPerfilRecurso() { return lstPerfilRecurso; }
    public void setLstPerfilRecurso(List<PerfilRecurso> lstPerfilRecurso) { this.lstPerfilRecurso = lstPerfilRecurso; }

    @Column(name = "tipoRecurso", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public TipoRecurso getTipoRecurso() { return tipoRecurso; }
    public void setTipoRecurso(TipoRecurso tipoRecurso) { this.tipoRecurso = tipoRecurso; }

    @Basic(optional = false)
    @Column(name = "recurso")
    public String getRecurso() { return recurso; }
    public void setRecurso(String recurso) { this.recurso = recurso; }

    @Basic(optional = true)
    @Column(name = "seguro")
    public Boolean getSeguro() { return seguro; }
    public void setSeguro(Boolean seguro) { this.seguro = seguro; }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Recurso rec = (Recurso) obj;

        return new EqualsBuilder()
                    .append(this.getId(), rec.getId())
                    .append(this.getRecurso(), rec.getRecurso())
                    .append(this.getSeguro(), rec.getSeguro())
                    .append(this.getTipoRecurso(), rec.getTipoRecurso())
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.getId())
                    .append(this.getRecurso())
                    .append(this.getSeguro())
                    .append(this.getTipoRecurso())
                    .toHashCode();

    }

}
