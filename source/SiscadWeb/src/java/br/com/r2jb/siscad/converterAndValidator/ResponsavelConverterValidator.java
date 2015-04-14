/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.facade.responsavel.ResponsavelFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Responsavel.class)
@FacesValidator(value="responsavelValidator")
public class ResponsavelConverterValidator extends BaseConverterValidator<Responsavel, ResponsavelFacade> {

}
