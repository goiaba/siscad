/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.util;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public enum Periodo {

    _0_ANUAL("ui.periodo.anual"),
    _1_SEMESTRE("ui.periodo.primeirosemestre"),
    _2_SEMESTRE("ui.periodo.segundosemestre");

    String descricao;

    private Periodo(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao() {

        return this.descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static Periodo getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(Periodo s : Periodo.values()) {

            if (s.getDescricao(locale).equals(descricao)) {

                return s;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaSemestre = new ArrayList<String>();

        for(Periodo s : Periodo.values()) {

            listaSemestre.add(s.getDescricao(locale));

        }

        return listaSemestre;

    }

    public static Periodo getSemestreAtual() {

        Integer mesAtual = Calendar.getInstance().get(Calendar.MONTH);

        if (mesAtual >= 0 && mesAtual < 7) {

            return Periodo._1_SEMESTRE;

        } else {

            return Periodo._2_SEMESTRE;

        }

    }

}
