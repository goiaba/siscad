/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.facade.professor.ProfessorFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Professor.class)
@FacesValidator(value="professorValidator")
public class ProfessorConverterValidator extends BaseConverterValidator<Professor, ProfessorFacade> {

}
