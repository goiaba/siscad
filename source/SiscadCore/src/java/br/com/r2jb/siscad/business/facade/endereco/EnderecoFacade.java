/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.endereco;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.PessoaEndereco;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.Collection;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface EnderecoFacade extends BaseFacade<Endereco> {

    void inserirEndereco(Endereco endereco);
    
    Endereco alterarEndereco(Endereco endereco);
    
    void removerEndereco(PessoaEndereco pessoaEndereco);

    void removerEndereco(Collection<PessoaEndereco> listaEndereco);

}
