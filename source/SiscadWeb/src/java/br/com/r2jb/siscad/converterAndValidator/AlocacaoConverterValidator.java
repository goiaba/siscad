/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.business.facade.alocacao.AlocacaoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Alocacao.class)
@FacesValidator(value="alocacaoValidator")
public class AlocacaoConverterValidator extends BaseConverterValidator<Alocacao, AlocacaoFacade> {

}
