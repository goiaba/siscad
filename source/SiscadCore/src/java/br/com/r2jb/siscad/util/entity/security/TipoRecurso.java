/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.entity.security;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public enum TipoRecurso implements Serializable {

    PAGINA("ui.resourceType.page");

    private String descricao;

    private TipoRecurso(String descricao) {

        this.descricao = descricao;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static TipoRecurso getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(TipoRecurso tr : TipoRecurso.values()) {

            if (tr.getDescricao(locale).equals(descricao)) {

                return tr;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaTipoRecurso = new ArrayList<String>();

        for(TipoRecurso tr : TipoRecurso.values()) {

            listaTipoRecurso.add(tr.getDescricao(locale));

        }

        return listaTipoRecurso;

    }

}
