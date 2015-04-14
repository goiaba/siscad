/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.escola;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.exception.EnderecoRequeridoException;
import br.com.r2jb.siscad.business.exception.EscolaContemDadosAcademicosException;
import br.com.r2jb.siscad.business.exception.EscolaJaCadastradaException;
import br.com.r2jb.siscad.business.exception.TelefoneRequeridoException;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface EscolaFacade extends BaseFacade<Escola> {

    Escola findByNome(String string);
    
    List<Escola> findEscolasPorProfessor(Professor professor);

    List<Escola> findByProfessorCursoSemestreEAno(Professor professor, Curso curso, Periodo semestre, Ano ano);

    boolean inserir(Escola escola, List<Endereco> enderecos, List<Telefone> telefones, List<Curso> cursos) throws EnderecoRequeridoException, TelefoneRequeridoException, EscolaJaCadastradaException;

    boolean isEscolaCadastrada(String cnpj);

    Escola findByCnpj(String cnpj);

    void alterar(Escola escola);

    void alterar(Escola entity, List<Curso> asList);

    void remover(Escola escola) throws EscolaContemDadosAcademicosException;

}
