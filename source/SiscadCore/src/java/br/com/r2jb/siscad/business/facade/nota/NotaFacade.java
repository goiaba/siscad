/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.nota;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface NotaFacade extends BaseFacade<Nota> {

    void registrarNotas(List<Nota> notas);

    Float isNotaJaRegistrada(Matricula matricula, Disciplina disciplina, Avaliacao avaliacao);

    Nota findByDisciplinaAvaliacaoEMatricula(String idDisciplina, String idAvaliacao, String idMatricula);

}
