/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.telefone.TelefoneFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Telefone.class)
@FacesValidator(value="telefoneValidator")
public class TelefoneConverterValidator extends BaseConverterValidator<Telefone, TelefoneFacade> {

}
