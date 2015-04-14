/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Curso.class)
@FacesValidator(value = "cursoValidator")
public class CursoConverterValidator extends BaseConverterValidator<Curso, CursoFacade> {

}
