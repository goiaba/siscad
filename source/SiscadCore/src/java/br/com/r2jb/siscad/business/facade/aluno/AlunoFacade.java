/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.aluno;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AlunoFacade extends BaseFacade<Aluno> {

    Aluno findByRA(String ra);

    List<Aluno> findByTurma(Turma turma);

    List<Aluno> findByNome(String nome);

    List<Aluno> findByNome(String nome, Boolean ativo);

    boolean alunoExiste(String ra);
    
    boolean inserirAluno(Aluno aluno, Locale locale);
    
    boolean inserirAlunoEResponsavel(Aluno aluno, Responsavel responsavel, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException;

    Aluno alterarAluno(Aluno aluno);

    void removerAluno(Aluno aluno);

    int gerarRA();

}
