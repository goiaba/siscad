/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.entity.security;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name="sec_perfilRecurso")
public class PerfilRecurso extends BaseEntity {

    private Integer id;
    private Perfil perfil;
    private Recurso recurso;

    @Id
    @SequenceGenerator(name = "PERFIL_RECURSO_ID_GEN", sequenceName = "sec_perfilRecurso_idPerfilRecurso_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFIL_RECURSO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idperfilrecurso")
    @Override
    public Integer getId() { return this.id; }
    @Override
    public void setId(Integer id) { this.id = id; }

    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    @JoinColumn(name = "idrecurso", referencedColumnName = "idrecurso")
    @ManyToOne(optional = false, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    public Recurso getRecurso() { return recurso; }
    public void setRecurso(Recurso recurso) { this.recurso = recurso; }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        PerfilRecurso pr = (PerfilRecurso) obj;

        return new EqualsBuilder()
                    .append(this.getId(), pr.getId())
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.getId())
                    .toHashCode();

    }

}
