/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.PessoaEndereco;
import br.com.r2jb.siscad.business.util.TipoEndereco;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import br.com.r2jb.siscad.ws.tempuri.CepResponseCepResult;
import br.com.r2jb.siscad.ws.tempuri.WSCepSoapProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import org.apache.axis.message.MessageElement;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class EnderecoBean<T> extends BaseBean {

    private Endereco endereco = new Endereco();
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    public void resetBean() {

        endereco = new Endereco();

        if (null != enderecos) {

            enderecos.clear();
            
        }

    }

    public String newEndereco() {

        setEndereco(new Endereco());

        return null;

    }

    public void addEndereco(Endereco endereco) {

        if (null == enderecos) {

            enderecos = new ArrayList<Endereco>();

        }

        enderecos.add(endereco);

    }

    public void complementaEnderecoPorCepListener() {

        try {

            String cep = endereco.getCep();
            
            if (null != cep) {

                CepResponseCepResult result = new WSCepSoapProxy().cep(cep);

                if (null != result) {

                    MessageElement messageElement = result.get_any()[1];

                    NodeList nlCep = messageElement.getAsDOM().getElementsByTagName("tbCEP");

                    if (null != nlCep && nlCep.getLength() > 0) {

                        for (int i = 0; i < nlCep.getLength(); i++) {

                            Element element = (Element) nlCep.item(i);

                            endereco.setRua(getTextValue(element, "logradouro") + " " + getTextValue(element, "nome"));
                            endereco.setBairro(getTextValue(element, "bairro"));
                            endereco.setCidade(getTextValue(element, "cidade"));
                            endereco.setEstado(getTextValue(element, "UF"));

                        }

                    }

                }

            }

        } catch (Exception ex) {

            Logger.getLogger(EnderecoBean.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public List<SelectItem> getListaTipoEndereco() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (TipoEndereco te : TipoEndereco.values()) {

            itens.add(new SelectItem(te, te.getDescricao(getLocale())));

        }

        return itens;

    }

    public List<SelectItem> getListaEstadosBrasileiros() {

        // TODO: Implementar
        List<SelectItem> itens = new ArrayList<SelectItem>();

        itens.add(new SelectItem("SP"));
        itens.add(new SelectItem("MG"));
        itens.add(new SelectItem("RJ"));
        itens.add(new SelectItem("ES"));
        itens.add(new SelectItem("SC"));
        itens.add(new SelectItem("RS"));

        return itens;

    }

    public boolean verifyAtLeastAnAddressInformed() {

        if (getEnderecos().isEmpty()) {

            addFacesMessage("enderecoNecessario", FacesMessage.SEVERITY_ERROR);
            return false;

        }

        return true;

    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the enderecos
     */
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     * @param enderecos the enderecos to set
     */
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void addEnderecos(List<PessoaEndereco> listaEndereco) {

        if (null == listaEndereco) {

            throw new RuntimeException(ResourceBundleUtils.getMessage(getLocale(), "error.msg.EB.sE.listaEnderecoNull"));

        }

        for (PessoaEndereco pe : listaEndereco) {

            this.addEndereco(pe.getEndereco());

        }

    }

    public void addEnderecosEscola(List<Endereco> listaEndereco) {

        if (null == listaEndereco) {

            throw new RuntimeException(ResourceBundleUtils.getMessage(getLocale(), "error.msg.EB.sE.listaEnderecoNull"));

        }

        this.enderecos = listaEndereco;

//        for (Endereco e : listaEndereco) {
//
//            this.addEndereco(e);
//
//        }

    }

    private String getTextValue(Element element, String tagName) {

        String textValue = null;

        NodeList nl = element.getElementsByTagName(tagName);

        if (nl != null && nl.getLength() > 0) {

            Element iEl = (Element) nl.item(0);

            textValue = iEl.getFirstChild().getNodeValue();
        }

        return textValue;

    }

}
