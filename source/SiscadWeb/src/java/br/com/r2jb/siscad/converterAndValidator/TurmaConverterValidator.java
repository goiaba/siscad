/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Turma.class)
@FacesValidator(value="turmaValidator")
public class TurmaConverterValidator extends BaseConverterValidator<Turma, TurmaFacade> {

}
