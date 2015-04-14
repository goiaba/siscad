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
public enum Sexo {

    MASCULINO("ui.sexo.masculino"),
    FEMININO("ui.sexo.feminino");

    String descricao;

    private Sexo(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static Sexo getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(Sexo s : Sexo.values()) {

            if (s.getDescricao(locale).equals(descricao)) {

                return s;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaSexo = new ArrayList<String>();

        for(Sexo s : Sexo.values()) {

            listaSexo.add(s.getDescricao(locale));

        }

        return listaSexo;

    }

}
