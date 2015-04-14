/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.facade.matricula.MatriculaFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Matricula.class)
@FacesValidator(value="matriculaValidator")
public class MatriculaConverterValidator extends BaseConverterValidator<Matricula, MatriculaFacade> {

}
