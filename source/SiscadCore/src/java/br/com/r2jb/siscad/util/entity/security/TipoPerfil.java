/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.util.entity.security;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public enum TipoPerfil implements Serializable {

    ADMINISTRADOR(0, "ui.perfil.administrador"),
    DIRETOR(1, "ui.perfil.diretor"),
    SECRETARIO(2, "ui.perfil.secretario"),
    PROFESSOR(3, "ui.perfil.professor"),
    RESPONSAVEL(4, "ui.perfil.responsavel"),
    ALUNO(5, "ui.perfil.aluno");

    public static Collection<TipoPerfil> perfisMenosAlunoProfessorEResponsavel() {
        
        List<TipoPerfil> lst = new ArrayList<TipoPerfil>();
        
        lst.add(ADMINISTRADOR);
        lst.add(DIRETOR);
        lst.add(SECRETARIO);
        
        return lst;
        
    }

    Integer codigo;
    String descricao;

    private TipoPerfil(Integer codigo, String descricao) {

        this.codigo = codigo;
        this.descricao = descricao;

    }

    public boolean aoMenosProfessor() {

        return this.getCodigo() <= TipoPerfil.PROFESSOR.getCodigo();

    }

    public boolean aoMenosSecretario() {

        return this.getCodigo() <= TipoPerfil.SECRETARIO.getCodigo();

    }

    boolean aoMenosDiretor() {

        return this.getCodigo() <= TipoPerfil.DIRETOR.getCodigo();

    }

    boolean aoMenosAdministrador() {

        return this.getCodigo() <= TipoPerfil.ADMINISTRADOR.getCodigo();

    }

    public Integer getCodigo() {

        return this.codigo;

    }

    public String getDescricao(Locale locale) {

        return ResourceBundleUtils.getMessage(locale, this.descricao);

    }

    public static TipoPerfil getFromDescricaoAndLocale(String descricao, Locale locale) {

        for(TipoPerfil s : TipoPerfil.values()) {

            if (s.getDescricao(locale).equals(descricao)) {

                return s;

            }

        }

        return null;

    }

    public static List<String> getLista(Locale locale) {

        List<String> listaTipoUsuario = new ArrayList<String>();

        for(TipoPerfil tu : TipoPerfil.values()) {

            listaTipoUsuario.add(tu.getDescricao(locale));

        }

        return listaTipoUsuario;

    }
    
}
