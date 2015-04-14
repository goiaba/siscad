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
public enum CodigoFuncao {

    PROFESSOR("ui.codigofuncao.professor"),
    RESPONSAVEL("ui.codigofuncao.responsavel");

    private String descricao;

    private CodigoFuncao(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static CodigoFuncao getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(CodigoFuncao sm : CodigoFuncao.values()) {

            if (sm.getDescricao(locale).equals(descricao)) {

                return sm;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaCodigoFuncao = new ArrayList<String>();

        for(CodigoFuncao t : CodigoFuncao.values()) {

            listaCodigoFuncao.add(t.getDescricao(locale));

        }

        return listaCodigoFuncao;

    }

}
