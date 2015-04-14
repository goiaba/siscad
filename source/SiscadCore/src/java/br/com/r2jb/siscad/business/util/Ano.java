/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.util;

import br.com.r2jb.siscad.business.exception.AnoInvalidoException;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public enum Ano {

    _2007(2007, "ui.ano.doismilesete"),
    _2008(2008, "ui.ano.doismileoito"),
    _2009(2009, "ui.ano.doismilenove"),
    _2010(2010, "ui.ano.doismiledez"),
    _2011(2011, "ui.ano.doismileonze"),
    _2012(2012, "ui.ano.doismiledoze"),
    _2013(2013, "ui.ano.doismiletreze"),
    _2014(2014, "ui.ano.doismilequatorze"),
    _2015(2015, "ui.ano.doismilequinze"),
    _2016(2016, "ui.ano.doismiledezesseis"),
    _2017(2017, "ui.ano.doismiledezessete"),
    _2018(2018, "ui.ano.doismiledezoito"),
    _2019(2019, "ui.ano.doismiledezenove"),
    _2020(2020, "ui.ano.doismilevinte"),
    _2021(2021, "ui.ano.doismilevinteeum");

    Integer valor;
    String descricao;

    private Ano(Integer valor, String descricao) {

        this.valor = valor;
        this.descricao = descricao;

    }

    public int getValor() {

        return this.valor;

    }

    public String getDescricao(Locale locale) {
        
        return ResourceBundleUtils.getMessage(locale, this.descricao);
        
    }

    public static String getDescricao(int iAno, Locale locale) throws AnoInvalidoException {

        for (Ano ano : Ano.values()) {

            if (ano.getValor() == iAno) {

                return ano.getDescricao(locale);

            }

        }

        throw new AnoInvalidoException("\'" + iAno + "\' não é um ano válido.");

    }

    public static Ano getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(Ano ano : Ano.values()) {

            if (ano.getDescricao(locale).equals(descricao)) {

                return ano;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaAno = new ArrayList<String>();

        for(Ano t : Ano.values()) {

            listaAno.add(t.getDescricao(locale));

        }

        return listaAno;

    }

    public static Ano getAnoAtual() {

        String anoAtual = "_" + Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

        return Ano.valueOf(anoAtual);

    }

}
