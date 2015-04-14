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
public enum TipoTelefone {

    RESIDENCIAL("ui.tipotelefone.residencial"),
    COMERCIAL("ui.tipotelefone.comercial"),
    CELULAR("ui.tipotelefone.celular"),
    RECADO("ui.tipotelefone.recado");

    String descricao;

    private TipoTelefone(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static TipoTelefone getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(TipoTelefone tt : TipoTelefone.values()) {

            if (tt.getDescricao(locale).equals(descricao)) {

                return tt;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaTipoTelefone = new ArrayList<String>();

        for(TipoTelefone t : TipoTelefone.values()) {

            listaTipoTelefone.add(t.getDescricao(locale));

        }

        return listaTipoTelefone;

    }

}
