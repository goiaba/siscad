/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Escola.class)
@FacesValidator(value="escolaValidator")
public class EscolaConverterValidator extends BaseConverterValidator<Escola, EscolaFacade> {

}
