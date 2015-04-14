/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.business.facade.nota.NotaFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Nota.class)
@FacesValidator(value="notaValidator")
public class NotaConverterValidator extends BaseConverterValidator<Nota, NotaFacade> {

}
