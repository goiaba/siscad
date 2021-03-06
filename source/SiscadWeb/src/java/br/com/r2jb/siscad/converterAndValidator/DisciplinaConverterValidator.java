/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Disciplina.class)
@FacesValidator(value="disciplinaValidator")
public class DisciplinaConverterValidator extends BaseConverterValidator<Disciplina, DisciplinaFacade>{

}
