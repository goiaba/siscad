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
@Table(name="sec_perfil")
public class Perfil extends BaseEntity {

    private Integer id;
    private TipoPerfil tipoPerfil;
    private List<PerfilRecurso> lstPerfilRecurso;

    @Id
    @SequenceGenerator(name = "PERFIL_ID_GEN", sequenceName = "sec_perfil_idperfil_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFIL_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idperfil")
    @Override
    public Integer getId() {return this.id;}
    @Override
    public void setId(Integer id) {this.id = id;}

    @Basic(optional = false)
    @Column(name = "perfil")
    @Enumerated(value = EnumType.STRING)
    public TipoPerfil getTipoPerfil() {return tipoPerfil;}
    public void setTipoPerfil(TipoPerfil perfil) {this.tipoPerfil = perfil;}

    @OneToMany(mappedBy = "perfil")
    public List<PerfilRecurso> getLstPerfilRecurso() { return lstPerfilRecurso; }
    public void setLstPerfilRecurso(List<PerfilRecurso> lstPerfilRecurso) { this.lstPerfilRecurso = lstPerfilRecurso; }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Perfil pfl = (Perfil) obj;

        return new EqualsBuilder()
                    .append(this.getId(), pfl.getId())
                    .append(this.getTipoPerfil(), pfl.getTipoPerfil())
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.getId())
                    .toHashCode();

    }

    @Override
    public String toString() {
        return this.tipoPerfil.descricao;
    }

}
