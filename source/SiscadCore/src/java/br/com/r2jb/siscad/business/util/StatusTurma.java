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
public enum StatusTurma {

    CRIADA("ui.statusturma.criada"),
    ABERTA("ui.statusturma.aberta"),
    FECHADA("ui.statusturma.fechada"),
    CONCLUIDA("ui.statusturma.concluida");

    private String descricao;

    private StatusTurma(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static StatusTurma getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(StatusTurma st : StatusTurma.values()) {

            if (st.getDescricao(locale).equals(descricao)) {

                return st;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaStatusTurma = new ArrayList<String>();

        for(StatusTurma t : StatusTurma.values()) {

            listaStatusTurma.add(t.getDescricao(locale));

        }

        return listaStatusTurma;

    }

}
