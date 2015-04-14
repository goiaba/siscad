/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class EntidadePossuiRelacionamentoException extends Exception {

    public enum Relacionamento {

        AVALIACAO_NOTA("ui.relacionamento.avaliacao_Nota"),
        PERIODOAVALIACAO_AVALIACAO("ui.relacionamento.periodoAvaliacao_Avaliacao"), 
        MATRIZCURRICULAR_DISCIPLINA("ui.relacionamento.matrizcurricular_disciplina");
        
        private String descricao;

        private Relacionamento(String descricao) {

            this.descricao = descricao;

        }

        public String getDescricao(Locale locale) {

            return ResourceBundleUtils.getMessage(locale, this.descricao);

        }

        public String getDescricao() {

            return this.descricao;
            
        }
        
    }

    private List<Relacionamento> relacionamentosPreenchidos;

    public EntidadePossuiRelacionamentoException() {

        super();

    }

    public EntidadePossuiRelacionamentoException(List<Relacionamento> relacionamentosPreenchidos) {

        super();
        this.relacionamentosPreenchidos = relacionamentosPreenchidos;

    }

    public EntidadePossuiRelacionamentoException(String message) {

        super(message);

    }

    public EntidadePossuiRelacionamentoException(String message, List<Relacionamento> relacionamentosPreenchidos) {

        super(message);
        this.relacionamentosPreenchidos = relacionamentosPreenchidos;

    }

    public EntidadePossuiRelacionamentoException(String message, Throwable t) {

        super(message, t);

    }

    public EntidadePossuiRelacionamentoException(String message, Throwable t, List<Relacionamento> relacionamentosPreenchidos) {

        super(message, t);
        this.relacionamentosPreenchidos = relacionamentosPreenchidos;

    }

    public List<Relacionamento> getRelacionamentosPreenchidos() {
        return relacionamentosPreenchidos;
    }

    public void addRelacionamentoPreenchido(Relacionamento relacionamento) {

        if (null == this.relacionamentosPreenchidos) {

            this.relacionamentosPreenchidos = new ArrayList<Relacionamento>();

        }

        this.relacionamentosPreenchidos.add(relacionamento);

    }

}
