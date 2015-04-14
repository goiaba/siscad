/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.TipoTelefone;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "telefone")
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByCodigoarea", query = "SELECT t FROM Telefone t WHERE t.codigoarea = :codigoarea"),
    @NamedQuery(name = "Telefone.findByNumero", query = "SELECT t FROM Telefone t WHERE t.numero = :numero"),
    @NamedQuery(name = "Telefone.findByTipo", query = "SELECT t FROM Telefone t WHERE t.tipo = :tipo")})
public class Telefone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String CODIGO_AREA = "codigoArea";
    public static final String NUMERO = "numeroTelefone";
    public static final String TIPO = "tipoTelefone";

    @Id
    @SequenceGenerator(name="TELEFONE_ID_GEN", sequenceName="telefone_idtelefone_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TELEFONE_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idtelefone")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigoarea")
    private int codigoarea;

    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;

    @Basic(optional = false)
    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoTelefone tipo;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "telefone")
    private List<EscolaTelefone> listaEscolaTelefone =  new ArrayList<EscolaTelefone>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "telefone")
    private List<PessoaTelefone> listaPessoaTelefone =  new ArrayList<PessoaTelefone>();

    public Telefone() {
    }

    public Telefone(Integer idtelefone) {
        this.id = idtelefone;
    }

    public Telefone(Integer idtelefone, int codigoarea, String numero, TipoTelefone tipo) {
        this.id = idtelefone;
        this.codigoarea = codigoarea;
        this.numero = numero;
        this.tipo = tipo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idtelefone) {
        this.id = idtelefone;
    }

    public int getCodigoarea() {
        return codigoarea;
    }

    public void setCodigoarea(int codigoarea) {
        this.codigoarea = codigoarea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public List<EscolaTelefone> getListaEscolaTelefone() {
        return listaEscolaTelefone;
    }

    public void setListaEscolaTelefone(List<EscolaTelefone> listaEscolaTelefone) {
        this.listaEscolaTelefone = listaEscolaTelefone;
    }

    public List<PessoaTelefone> getListaPessoaTelefone() {
        return Collections.unmodifiableList(listaPessoaTelefone);
    }

    public void internalAddPessoaTelefone(PessoaTelefone pessoaTelefone) {

        listaPessoaTelefone.add(pessoaTelefone);

    }

    public void internalRemovePessoaTelefone(PessoaTelefone pessoaTelefone) {

        listaPessoaTelefone.remove(pessoaTelefone);

    }

    public void addPessoaTelefone(PessoaTelefone pessoaTelefone) {

        pessoaTelefone.setTelefone(this);

    }

    public void removePessoaTelefone(PessoaTelefone pessoaTelefone) {

        pessoaTelefone.setTelefone(null);

    }

    public void setListaPessoaTelefone(List<PessoaTelefone> listaPessoaTelefone) {
        this.listaPessoaTelefone = listaPessoaTelefone;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Telefone telefone = (Telefone) obj;

        return new EqualsBuilder()
                    .append(this.codigoarea, telefone.codigoarea)
                    .append(this.id, telefone.id)
                    .append(this.numero, telefone.numero)
                    .append(this.tipo, telefone.tipo)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.codigoarea)
                    .append(this.numero)
                    .append(this.tipo)
                    .toHashCode();

    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Telefone[idtelefone=" + id + "]";
    }

}
