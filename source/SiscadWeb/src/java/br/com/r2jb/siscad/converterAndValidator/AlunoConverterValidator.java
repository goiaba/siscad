/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.facade.aluno.AlunoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Aluno.class)
@FacesValidator(value="alunoValidator")
public class AlunoConverterValidator extends BaseConverterValidator<Aluno, AlunoFacade> {

}
