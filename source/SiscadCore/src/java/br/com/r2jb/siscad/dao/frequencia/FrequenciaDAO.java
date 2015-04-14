/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.frequencia;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Frequencia;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.dao.base.BaseDAO;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface FrequenciaDAO extends BaseDAO<Frequencia> {

    Float isFrequenciaJaRegistrada(Matricula matricula, Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao);

    Frequencia findByDisciplinaAvaliacaoEMatricula(Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao, Matricula matricula);
    
}
