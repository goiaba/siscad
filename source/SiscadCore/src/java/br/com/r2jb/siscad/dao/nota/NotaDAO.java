/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.nota;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.dao.base.BaseDAO;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface NotaDAO extends BaseDAO<Nota> {

    Float isNotaJaRegistrada(Matricula matricula, Disciplina disciplina, Avaliacao avaliacao);

    Nota findByDisciplinaAvaliacaoEMatricula(Disciplina disciplina, Avaliacao avaliacao, Matricula matricula);
    
}
