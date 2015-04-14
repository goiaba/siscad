/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.facade.endereco.EnderecoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Endereco.class)
@FacesValidator(value="enderecoValidator")
public class EnderecoConverterValidator extends BaseConverterValidator<Endereco, EnderecoFacade> {

}
