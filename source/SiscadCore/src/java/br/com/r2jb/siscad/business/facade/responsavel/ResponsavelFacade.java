/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.responsavel;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.exception.ResponsavelPossuiDependentesException;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface ResponsavelFacade extends BaseFacade<Responsavel> {

    Responsavel findByCPF(String cpf);

    List<Responsavel> findByNome(String nome);

    List<Responsavel> findByNome(String nome, Boolean ativo);

    boolean responsavelExiste(String cpf);

    boolean inserirResponsavel(Responsavel responsavel, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException;

    Responsavel alterarResponsavel(Responsavel responsavel);

    void removerResponsavel(Responsavel responsavel) throws ResponsavelPossuiDependentesException;

}
