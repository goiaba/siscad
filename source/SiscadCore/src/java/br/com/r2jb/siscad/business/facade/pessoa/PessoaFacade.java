/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.pessoa;

import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PessoaFacade extends BaseFacade<Pessoa> {

    Pessoa findByCPF(String cpf);

}
