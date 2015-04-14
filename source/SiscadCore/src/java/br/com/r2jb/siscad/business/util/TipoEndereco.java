/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.util;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public enum TipoEndereco {

    COMERCIAL("ui.tipoendereco.comercial"),
    RESIDENCIAL("ui.tipoendereco.residencial"),
    ENTREGA("ui.tipoendereco.entrega"),
    COBRANCA("ui.tipoendereco.cobranca");

    String descricao;

    private TipoEndereco(String descricao) {

        this.descricao = descricao;
        
    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);
        
    }

    public static TipoEndereco getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(TipoEndereco te : TipoEndereco.values()) {

            if (te.getDescricao(locale).equals(descricao)) {

                return te;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaTipoEndereco = new ArrayList<String>();

        for(TipoEndereco t : TipoEndereco.values()) {

            listaTipoEndereco.add(t.getDescricao(locale));

        }

        return listaTipoEndereco;

    }

}
