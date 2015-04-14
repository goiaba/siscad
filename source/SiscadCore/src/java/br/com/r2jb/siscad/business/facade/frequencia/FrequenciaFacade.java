/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.frequencia;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Frequencia;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface FrequenciaFacade extends BaseFacade<Frequencia> {

    void registrarFrequencias(List<Frequencia> frequencias);

    Float isFrequenciaJaRegistrada(Matricula matricula, Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao);

    Frequencia findByDisciplinaAvaliacaoEMatricula(String idDisciplina, String idPeriodoAvaliacao, String idMatricula);

}
