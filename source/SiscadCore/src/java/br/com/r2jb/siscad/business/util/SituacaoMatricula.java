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
public enum SituacaoMatricula {

    MATRICULADO("ui.situacaomatricula.matriculado"),
    TRANSFERIDO("ui.situacaomatricula.transferido"),
    RECLASSIFICADO("ui.situacaomatricula.reclassificado"),
    REMANEJADO("ui.situacaomatricula.remanejado"),
    APROVADO("ui.situacaomatricula.aprovado"),
    RETIDO("ui.situacaomatricula.retido");

    private String descricao;

    private SituacaoMatricula(String descricao) {

        this.descricao = descricao;

    }
    
    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);
        
    }

    public static SituacaoMatricula getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(SituacaoMatricula sm : SituacaoMatricula.values()) {

            if (sm.getDescricao(locale).equals(descricao)) {

                return sm;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaSituacaoMatricula = new ArrayList<String>();

        for(SituacaoMatricula t : SituacaoMatricula.values()) {

            listaSituacaoMatricula.add(t.getDescricao(locale));

        }

        return listaSituacaoMatricula;

    }

}
