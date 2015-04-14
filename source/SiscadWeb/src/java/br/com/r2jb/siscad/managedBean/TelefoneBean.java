/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.EscolaTelefone;
import br.com.r2jb.siscad.business.entity.PessoaTelefone;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.util.TipoTelefone;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ricardo
 */
@ManagedBean	
@ViewScoped
public class TelefoneBean extends BaseBean {

    private Telefone telefone = new Telefone();
    private List<Telefone> telefones = new ArrayList<Telefone>();

    public void resetBean() {

        telefone = new Telefone();

        if (null != telefones) {

            telefones.clear();

        }

    }

    public String newTelefone() {

        setTelefone(new Telefone());

        return null;

    }

    public void addTelefone(Telefone telefone) {

        if (null == this.telefones) {

            this.telefones = new ArrayList<Telefone>();

        }

        this.telefones.add(telefone);

    }

    public List<SelectItem> getListaTipoTelefone() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for(TipoTelefone tt : TipoTelefone.values()) {

            itens.add(new SelectItem(tt, tt.getDescricao(getLocale())));

        }

        return itens;

    }

    public boolean verifyAtLeastATelephoneInformed() {

        if (getTelefones().isEmpty()) {

            addFacesMessage("telefoneNecessario", FacesMessage.SEVERITY_ERROR);
            return false;

        }

        return true;

    }

    /**
     * @return the telefone
     */
    public Telefone getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the telefones
     */
    public List<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * @param telefones the telefones to set
     */
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    void addTelefones(List<PessoaTelefone> listaTelefone) {

        if (null == listaTelefone) {

            throw new RuntimeException(ResourceBundleUtils.getMessage(getLocale(), "error.msg.TB.sT.listaTelefoneNull"));

        }

        for (PessoaTelefone pt : listaTelefone) {

            this.addTelefone(pt.getTelefone());

        }

    }

    void addTelefonesEscola(List<EscolaTelefone> listaTelefone) {

        if (null == listaTelefone) {

            throw new RuntimeException(ResourceBundleUtils.getMessage(getLocale(), "error.msg.TB.sT.listaTelefoneNull"));

        }

        for (EscolaTelefone et : listaTelefone) {

            this.addTelefone(et.getTelefone());

        }

    }

}
