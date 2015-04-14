/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.professor;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface ProfessorFacade extends BaseFacade<Professor> {

    Professor findByCpf(String cpf);

    Professor findByMatricula(Integer matricula);

    List<Professor> findByNome(String nome);

    List<Professor> findByNome(String nome, Boolean ativo);

    boolean professorExiste(String cpf);

    boolean inserirProfessor(Professor professor, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException;

    Professor alterarProfessor(Professor professor);

    void removerProfessor(Professor professor);

}
