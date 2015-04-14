/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.escola;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface EscolaDAO extends BaseDAO<Escola> {

    Escola findByNome(String nomeEscola);

    List<Escola> findEscolasPorProfessor(Professor professor);

    List<Escola> findEscolas(Professor professor, Curso curso, Periodo semestre, Ano ano);

    Escola findByCnpj(String cnpj);

}
