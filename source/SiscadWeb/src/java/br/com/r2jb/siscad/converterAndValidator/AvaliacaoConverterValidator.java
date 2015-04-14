/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.facade.avaliacao.AvaliacaoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Avaliacao.class)
@FacesValidator(value="avaliacaoValidator")
public class AvaliacaoConverterValidator extends BaseConverterValidator<Avaliacao, AvaliacaoFacade> {

}
