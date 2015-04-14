/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.telefone;

import br.com.r2jb.siscad.business.entity.PessoaTelefone;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.Collection;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface TelefoneFacade extends BaseFacade<Telefone> {

    void inserirTelefone(Telefone telefone);

    Telefone alterarTelefone(Telefone telefone);

    void removerTelefone(PessoaTelefone pessoaTelefone);

    void removerTelefone(Collection<PessoaTelefone> listaTelefone);

}
