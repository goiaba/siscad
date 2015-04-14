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
public enum EstadoCivil {

    CASADO("ui.estadocivil.casado"),
    DESQUITADO("ui.estadocivil.desquitado"),
    DIVORCIADO("ui.estadocivil.divorciado"),
    EM_UNIAO_ESTAVEL("ui.estadocivil.emuniaoestavel"),
    SEPARADO_JUDICIALMENTE("ui.estadocivil.separadojudicialmente"),
    SOLTEIRO("ui.estadocivil.solteiro"),
    VIUVO("ui.estadocivil.viuvo");

    String descricao;

    private EstadoCivil(String descricao) {

        this.descricao = descricao;
        
    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);
        
    }

    public static EstadoCivil getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(EstadoCivil es : EstadoCivil.values()) {

            if (es.getDescricao(locale).equals(descricao)) {

                return es;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> result = new ArrayList<String>();

        for (EstadoCivil estadoCivil : EstadoCivil.values()) {

            result.add(estadoCivil.getDescricao(locale));

        }

        return result;

    }

}
